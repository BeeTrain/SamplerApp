/* Project Settings */

val moduleConfiguratorPath = "$rootDir/gradle/modules-configurator.gradle.kts"

// Attach modules
apply(from = File(moduleConfiguratorPath))

dependencyResolutionManagement {

    @Suppress("UnstableApiUsage")
    repositories {
        google()
        mavenCentral()
    }
}

pluginManagement {
    repositories {
        google()
        mavenCentral()
    }

    resolutionStrategy {
        eachPlugin {
            val kotlinVersion = "1.5.21"
            val androidGradleVersion = "7.0.1"

            val pluginId = requested.id.id
            when {
                pluginId.startsWith("org.jetbrains.kotlin") -> useVersion(kotlinVersion)
                pluginId.startsWith("com.android.") -> {
                    useModule("com.android.tools.build:gradle:$androidGradleVersion")
                }
            }
        }
    }
}