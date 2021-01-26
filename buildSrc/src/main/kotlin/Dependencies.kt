import extensions.compileOnly
import extensions.implementation
import org.gradle.api.JavaVersion
import org.gradle.api.artifacts.dsl.DependencyHandler

val javaVersion = JavaVersion.VERSION_1_8

object BuildPlugins {
    const val androidApplication = "com.android.application"
    const val androidLibrary = "com.android.library"

    const val javaLibrary = "java-library"

    const val kotlin = "kotlin"
    const val kotlinAndroid = "kotlin-android"
    const val androidModule = "android-module"
    const val kotlinKapt = "kotlin-kapt"
    const val kotlinJVM = "org.jetbrains.kotlin.jvm"

    const val navigation = "androidx.navigation.safeargs.kotlin"
    const val dependenciesVersions = "com.github.ben-manes.versions"

    const val ktlint = "org.jlleitschuh.gradle.ktlint"
    const val detekt = "io.gitlab.arturbosch.detekt"
}

object KotlinDependencies {
    object Versions {
        const val kotlinVersion = "1.4.21"
        const val kotlinCoroutines = "1.3.4"
    }

    const val stdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlinVersion}"
    const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlinCoroutines}"
    const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.kotlinCoroutines}"
    const val coroutinesTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.kotlinCoroutines}"

    fun defaultModuleLibs(dependencies: DependencyHandler) = dependencies.apply {
        implementation(stdLib)
    }

    fun coroutines(dependencies: DependencyHandler) = dependencies.apply {
        implementation(coroutinesCore)
        implementation(coroutinesAndroid)
        implementation(coroutinesTest)
    }

    fun all(dependencies: DependencyHandler) = dependencies.apply {
        defaultModuleLibs(this)
        coroutines(this)
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
        const val appCompatVersion = "1.2.0"
        const val coreVersion = "1.3.2"
        const val materialVersion = "1.2.1"
        const val constraintLayoutVersion = "2.0.4"
        const val lifecycleVersion = "2.2.0"
        const val navigationVersion = "2.3.2"
    }

    const val coreKtx = "androidx.core:core-ktx:${Versions.coreVersion}"
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompatVersion}"
    const val material = "com.google.android.material:material:${Versions.materialVersion}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayoutVersion}"
    const val lifecycle = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycleVersion}"

    const val navigationFragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navigationVersion}"
    const val navigationUi = "androidx.navigation:navigation-ui-ktx:${Versions.navigationVersion}"

    fun core(dependencies: DependencyHandler) = dependencies.apply {
        implementation(coreKtx)
    }

    fun ui(dependencies: DependencyHandler) = dependencies.apply {
        core(this)
        implementation(appCompat)
        implementation(material)
        implementation(constraintLayout)
    }

    fun navigation(dependencies: DependencyHandler) = dependencies.apply {
        implementation(navigationFragment)
        implementation(navigationUi)
    }

    fun all(dependencies: DependencyHandler) = dependencies.apply {
        ui(this)
        navigation(this)
        implementation(lifecycle)
    }
}

object DIDependencies {
    object Versions {
        const val koinVersion = "2.2.2"
    }

    const val core = "org.koin:koin-core:${Versions.koinVersion}"
    const val ext = "org.koin:koin-core-ext:${Versions.koinVersion}"
    const val android = "org.koin:koin-android:${Versions.koinVersion}"
    const val viewModel = "org.koin:koin-androidx-viewmodel:${Versions.koinVersion}"
    const val scope = "org.koin:koin-androidx-scope:${Versions.koinVersion}"
    const val test = "org.koin:koin-test:${Versions.koinVersion}"

    fun all(dependencies: DependencyHandler) = dependencies.apply {
        implementation(core)
        implementation(ext)
        implementation(android)
        implementation(viewModel)
        implementation(scope)
        implementation(test)
    }
}

object MiscDependencies {
    object Versions {
        const val timberVersion = "4.7.1"
    }

    const val timber = "com.jakewharton.timber:timber:${Versions.timberVersion}"

    fun timber(dependencies: DependencyHandler) = dependencies.apply {
        implementation(timber)
    }

    fun all(dependencies: DependencyHandler) = dependencies.apply {
        timber(this)
    }
}