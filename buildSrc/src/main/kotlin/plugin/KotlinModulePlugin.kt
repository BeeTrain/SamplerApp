package plugin

import Plugins
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

class KotlinModulePlugin: Plugin<Project> {

    override fun apply(project: Project) {
        project.run {
            plugins.run {
                apply(Plugins.androidKotlin)
            }

            tasks.withType<KotlinCompile> {
                kotlinOptions {
                    jvmTarget = JavaVersion.VERSION_1_8.toString()
                }
            }
        }
    }
}