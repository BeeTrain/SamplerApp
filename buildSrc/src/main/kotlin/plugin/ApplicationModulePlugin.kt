package plugin

import AndroidXDependencies
import BuildTypes
import Config
import DIDependencies
import KotlinDependencies
import MiscDependencies
import Plugins
import SourceSets
import extension.featureModulesDirectory
import extension.isGradleProjectDir
import internal.applicationExtension
import internal.baseModulesDirectory
import internal.getGitVersionCode
import internal.getGitVersionName
import internal.implementation
import internal.lintChecks
import internal.setupDependencyUpdatesTask
import internal.setupQualityTask
import java.io.File
import javaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.project

class ApplicationModulePlugin : Plugin<Project> {

    override fun apply(target: Project) {
        target.run {
            applyPlugins()
            applyApplicationConfig()
            applyDependencies()
            setupQualityTask()
            setupDependencyUpdatesTask()
        }
    }

    private fun Project.applyPlugins() {
        plugins.run {
            apply(plugin = Plugins.androidApplication)
            apply(plugin = Plugins.kotlinModule)
            apply(plugin = Plugins.dependenciesVersions)
            apply(plugin = Plugins.detekt)
            apply(plugin = Plugins.ktlint)
        }
    }

    private fun Project.applyApplicationConfig() {
        applicationExtension.apply {
            compileSdk = Config.compileSdkVersion
            buildToolsVersion = Config.buildToolsVersion

            defaultConfig {
                applicationId = Config.applicationId
                minSdk = Config.minSdkVersion
                targetSdk = Config.targetSdkVersion
                versionCode = getGitVersionCode()
                versionName = getGitVersionName()
            }

            buildTypes {
                getByName(BuildTypes.debug) {
                    isMinifyEnabled = false
                }

                getByName(BuildTypes.release) {
                    isMinifyEnabled = true
                }
            }

            SourceSets.values().forEach { sourceSet ->
                sourceSets.named(sourceSet.title).configure {
                    java.srcDirs(sourceSet.path)
                }
            }

            compileOptions {
                sourceCompatibility = javaVersion
                targetCompatibility = javaVersion
            }
        }
    }

    private fun Project.applyDependencies() {
        dependencies.apply {
            // Custom Lint rules
            lintChecks(project(Config.Modules.LINT.path))

            // Base modules
            File(baseModulesDirectory).listFiles()?.forEach { baseModule ->
                if (baseModule.isDirectory && baseModule.isGradleProjectDir) {
                    implementation(project(":${baseModule.name}"))
                }
            }

            // Feature modules
            File(featureModulesDirectory).listFiles()?.forEach { featureModule ->
                if (featureModule.isDirectory && featureModule.isGradleProjectDir) {
                    implementation(project(":${featureModule.name}"))
                }
            }

            // Libraries
            implementation(KotlinDependencies.stdLib)
            AndroidXDependencies.all(this)
            DIDependencies.all(this)
            MiscDependencies.all(this)
        }
    }
}