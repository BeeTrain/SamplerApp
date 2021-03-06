package config

import ApplicationConfig
import BuildPlugins
import BuildTypes
import KotlinDependencies.defaultModuleLibs
import MiscDependencies.timber
import SourceSets
import com.android.build.gradle.BaseExtension
import extensions.lintChecks
import extensions.setupQualityTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.exclude
import org.gradle.kotlin.dsl.project
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

class ModulePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.run {

            apply(plugin = BuildPlugins.androidLibrary)
            apply(plugin = BuildPlugins.kotlinAndroid)
            apply(plugin = BuildPlugins.kotlinKapt)

            val android = extensions.findByName("android") as BaseExtension

            android.apply {
                compileSdkVersion(ApplicationConfig.compileSdkVersion)

                defaultConfig {
                    minSdkVersion(ApplicationConfig.minSdkVersion)
                    targetSdkVersion(ApplicationConfig.targetSdkVersion)
                }

                buildTypes.getByName(BuildTypes.debug) {
                    isDebuggable = true
                    isDefault = true
                }

                buildTypes.getByName(BuildTypes.release) {
                    isDebuggable = false
                }

                sourceSets.named(SourceSets.main).configure {
                    java.srcDirs(SourceSets.srcMain)
                }

                compileOptions {
                    sourceCompatibility = ApplicationConfig.javaVersion
                    targetCompatibility = ApplicationConfig.javaVersion
                }

                configurations.all {
                    resolutionStrategy {
                        exclude("org.jetbrains.kotlinx", "kotlinx-coroutines-debug")
                    }
                }
            }

            tasks.withType<KotlinCompile> {
                kotlinOptions.jvmTarget = ApplicationConfig.javaVersion.toString()
            }

            setupQualityTask()
        }
    }
}

fun DependencyHandler.setupDefaultModuleDependencies() {
    lintChecks(project(ApplicationConfig.Modules.LINT.path))
    timber()
    defaultModuleLibs()
}