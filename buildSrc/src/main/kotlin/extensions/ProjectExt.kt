package extensions

import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask
import java.util.*
import org.gradle.api.Project

fun Project.setupQualityTask() = apply {
    tasks.register("checkBeforePush").configure {
        group = "verification"
        description = "Inspect your code before push"
        dependsOn("lint", "ktlintCheck", "detekt")
    }
}

fun Project.setupDependencyUpdatesTask() = apply {
    tasks.named("dependencyUpdates", DependencyUpdatesTask::class.java).configure {
        rejectVersionIf {
            isNonStable(candidate.version)
        }

        gradleReleaseChannel = "current"
        checkForGradleUpdate = true
        outputFormatter = "html"
        outputDir = "build/reports/dependencyUpdates"
        reportfileName = "report"
    }
}

val Project.featureModulesDirectory
    get() = "${rootDir}/source/feature"

val Project.baseModulesDirectory
    get() = "${rootDir}/source/base"

private fun isNonStable(version: String): Boolean {
    val stableKeyword = listOf("RELEASE", "FINAL", "GA").any { version.toUpperCase(Locale.getDefault()).contains(it) }
    val regex = "^[0-9,.v-]+(-r)?$".toRegex()
    val isStable = stableKeyword || regex.matches(version)
    return isStable.not()
}