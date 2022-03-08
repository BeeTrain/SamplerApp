package internal

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.LibraryExtension
import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask
import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import java.util.Locale
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.withType
import org.jlleitschuh.gradle.ktlint.KtlintExtension
import org.jlleitschuh.gradle.ktlint.reporter.ReporterType

internal val Project.applicationExtension: ApplicationExtension
    get() = extensions.findByName("android") as ApplicationExtension

internal val Project.libraryExtension: LibraryExtension
    get() = extensions.findByName("android") as LibraryExtension

internal fun Project.setupQualityTask() = apply {
    configure<DetektExtension> {
        config = files("$rootDir/config/quality/detekt-config.yml")
    }
    configure<KtlintExtension> {
        outputToConsole.set(true)
        outputColorName.set("RED")

        reporters {
            reporter(ReporterType.HTML)
            reporter(ReporterType.PLAIN)
        }
        filter {
            exclude("**/generated/**")
            include("**/kotlin/**")
        }
    }
    val androidExtension = extensions.findByName("android") as CommonExtension<*, *, *, *>
    androidExtension.apply {
        lint {
            disable += listOf(
                "RtlSymmetry",
                "RtlHardcoded",
                "InvalidPackage",
                "ParcelCreator",
                "AppLinkUrlError",
                "MissingTranslation",
                "DuplicatePlatformClasses",
                "CheckResult",
                "Instantiatable"
            )
        }
    }
    tasks.register("checkBeforePush").configure {
        group = "verification"
        description = "Inspect your code before push"
        dependsOn("lint", "ktlintCheck", "detekt")
    }
}

internal fun Project.setupDependencyUpdatesTask() = apply {
    tasks.withType<DependencyUpdatesTask> {
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

internal val Project.baseModulesDirectory
    get() = "$rootDir/source/base"

private fun isNonStable(version: String): Boolean {
    val stableKeyword = listOf("RELEASE", "FINAL", "GA").any { version.toUpperCase(Locale.getDefault()).contains(it) }
    val regex = "^[0-9,.v-]+(-r)?$".toRegex()
    val isStable = stableKeyword || regex.matches(version)
    return isStable.not()
}