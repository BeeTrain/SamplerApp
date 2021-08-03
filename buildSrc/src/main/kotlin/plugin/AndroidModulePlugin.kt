package plugin

import BuildTypes
import Config
import KotlinDependencies.defaultModuleLibs
import MiscDependencies.timber
import Plugins
import SourceSets
import internal.libraryExtension
import internal.lintChecks
import internal.setupQualityTask
import javaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.exclude
import org.gradle.kotlin.dsl.project

class AndroidModulePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.run {
            applyPlugins()
            applyLibraryConfig()
            applyDefaultDependencies()
            setupQualityTask()
        }
    }

    private fun Project.applyPlugins() {
        apply(plugin = Plugins.androidLibrary)
        apply(plugin = Plugins.androidKotlin)
        apply(plugin = Plugins.kotlinKapt)
        apply(plugin = Plugins.detekt)
        apply(plugin = Plugins.ktlint)
    }

    private fun Project.applyLibraryConfig() {
        libraryExtension.apply {
            compileSdk = Config.compileSdkVersion

            defaultConfig {
                minSdk = Config.minSdkVersion
                targetSdk = Config.targetSdkVersion
            }

            buildTypes.getByName(BuildTypes.debug) {
                isDefault = true
            }

            buildTypes.getByName(BuildTypes.release) {
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

            configurations.all {
                resolutionStrategy {
                    exclude("org.jetbrains.kotlinx", "kotlinx-coroutines-debug")
                }
            }
        }
    }

    private fun Project.applyDefaultDependencies() {
        dependencies.apply {
            lintChecks(project(Config.Modules.LINT.path))
            timber()
            defaultModuleLibs()
        }
    }
}