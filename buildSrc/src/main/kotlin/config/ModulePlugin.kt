package config

import ApplicationConfig
import BuildTypes
import KotlinDependencies
import MiscDependencies
import SourceSets
import com.android.build.gradle.BaseExtension
import extensions.lintChecks
import extensions.setupQualityTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.project
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

class ModulePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.run {
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
    MiscDependencies.timber(this)
    KotlinDependencies.defaultModuleLibs(this)
}