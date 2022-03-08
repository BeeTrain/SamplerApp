@file:Suppress("MemberVisibilityCanBePrivate")

import internal.compileOnly
import internal.implementation
import org.gradle.api.JavaVersion
import org.gradle.api.artifacts.dsl.DependencyHandler

val javaVersion = JavaVersion.VERSION_1_8

object Plugins {
    const val applicationModule = "application-module"
    const val androidModule = "android-module"
    const val kotlinModule = "kotlin-module"

    const val androidApplication = "com.android.application"
    const val androidLibrary = "com.android.library"
    const val androidKotlin = "kotlin-android"

    const val javaLibrary = "java-library"
    const val kotlin = "kotlin"
    const val kotlinKapt = "kotlin-kapt"
    const val kotlinJVM = "org.jetbrains.kotlin.jvm"

    const val navigation = "androidx.navigation.safeargs.kotlin"
    const val dependenciesVersions = "com.github.ben-manes.versions"

    const val ktlint = "org.jlleitschuh.gradle.ktlint"
    const val detekt = "io.gitlab.arturbosch.detekt"
}

object KotlinDependencies {
    object Versions {
        const val kotlinVersion = "1.6.10"
        const val kotlinCoroutines = "1.6.0"
    }

    const val stdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlinVersion}"
    const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlinCoroutines}"
    const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.kotlinCoroutines}"
    const val coroutinesTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.kotlinCoroutines}"

    fun DependencyHandler.defaultModuleLibs() = apply {
        implementation(stdLib)
    }

    fun DependencyHandler.coroutines() = apply {
        implementation(coroutinesCore)
        implementation(coroutinesAndroid)
        implementation(coroutinesTest)
    }

    fun all(dependencies: DependencyHandler) = dependencies.apply {
        defaultModuleLibs()
        coroutines()
    }
}

object LintDependencies {
    object Versions {
        const val lint = "27.1.1"
    }

    const val lintApi = "com.android.tools.lint:lint-api:${Versions.lint}"
    const val lintChecks = "com.android.tools.lint:lint-checks:${Versions.lint}"

    fun all(dependencies: DependencyHandler) = dependencies.apply {
        compileOnly(lintApi)
        compileOnly(lintChecks)
    }
}

object AndroidXDependencies {
    object Versions {
        const val appCompat = "1.4.1"
        const val core = "1.7.0"
        const val material = "1.5.0"
        const val constraintLayout = "2.1.3"
        const val lifecycle = "2.4.1"
        const val navigation = "2.4.1"
        const val recyclerView = "1.2.1"
    }

    const val coreKtx = "androidx.core:core-ktx:${Versions.core}"
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val recyclerView = "androidx.recyclerview:recyclerview:${Versions.recyclerView}"
    const val lifecycle = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"

    const val navigationFragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val navigationUi = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"

    fun DependencyHandler.core() = apply {
        implementation(coreKtx)
    }

    fun DependencyHandler.ui() = apply {
        core()
        implementation(appCompat)
        implementation(material)
        implementation(recyclerView)
        implementation(constraintLayout)
    }

    fun DependencyHandler.navigation() = apply {
        implementation(navigationFragment)
        implementation(navigationUi)
    }

    fun all(dependencies: DependencyHandler) = dependencies.apply {
        ui()
        navigation()
        implementation(lifecycle)
    }
}

object DIDependencies {
    object Versions {
        const val koinVersion = "3.1.5"
    }

    const val core = "io.insert-koin:koin-core:${Versions.koinVersion}"
    const val android = "io.insert-koin:koin-android:${Versions.koinVersion}"

    fun all(dependencies: DependencyHandler) = dependencies.apply {
        implementation(core)
        implementation(android)
    }
}

object MiscDependencies {
    object Versions {
        const val timber = "5.0.1"
        const val lottie = "5.0.3"
    }

    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
    const val lottie = "com.airbnb.android:lottie:${Versions.lottie}"

    fun DependencyHandler.timber() = apply {
        implementation(timber)
    }

    fun DependencyHandler.lottie() = apply {
        implementation(lottie)
    }

    fun all(dependencies: DependencyHandler) = dependencies.apply {
        timber()
        lottie()
    }
}