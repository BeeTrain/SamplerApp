package extensions

import org.gradle.api.Project

fun Project.setupQualityTask() = apply {
    tasks.register("checkBeforePush").configure {
        group = "verification"
        description = "Inspect your code before push"
        dependsOn("lint", "ktlintCheck", "detekt")
    }
}