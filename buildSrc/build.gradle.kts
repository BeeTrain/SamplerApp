buildscript {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

plugins {
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}

repositories {
    google()
    mavenCentral()
    maven("https://plugins.gradle.org/m2/")
}

val kotlinVersion = "1.6.10"
val androidGradlePluginVersion = "7.1.2"
val navigationVersion = "2.3.5"
val ktlintVersion = "10.1.0"
val detektVersion = "1.19.0"
val dependenciesVersion = "0.42.0"

dependencies {
    compileOnly(gradleApi())

    implementation("com.android.tools.build:gradle:$androidGradlePluginVersion")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")

    implementation("androidx.navigation:navigation-safe-args-gradle-plugin:$navigationVersion")
    implementation("org.jlleitschuh.gradle:ktlint-gradle:$ktlintVersion")
    implementation("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:$detektVersion")
    implementation("com.github.ben-manes:gradle-versions-plugin:$dependenciesVersion")
}

gradlePlugin {
    plugins {
        register("kotlin-module") {
            id = "kotlin-module"
            description = "Gradle plugin for kotlin module."
            implementationClass = "plugin.KotlinModulePlugin"
        }
        register("application-module") {
            id = "application-module"
            description = "Gradle plugin for android application module."
            implementationClass = "plugin.ApplicationModulePlugin"
        }
        register("android-module") {
            id = "android-module"
            description = "Gradle plugin for android library module."
            implementationClass = "plugin.AndroidModulePlugin"
        }
    }
}