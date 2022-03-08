/* Project Settings */

val modulesConfiguratorPath = "$rootDir/gradle/modules-configurator.gradle.kts"

// Attach modules
apply(from = File(modulesConfiguratorPath))

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
            val kotlinVersion = "1.6.10"
            val androidGradleVersion = "7.1.2"

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