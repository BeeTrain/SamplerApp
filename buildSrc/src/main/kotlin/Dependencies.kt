import extensions.implementation
import org.gradle.api.JavaVersion
import org.gradle.api.artifacts.dsl.DependencyHandler

val javaVersion = JavaVersion.VERSION_1_8

object BuildPlugins {
    const val androidApplication = "com.android.application"
    const val androidLibrary = "com.android.library"

    const val kotlinAndroid = "kotlin-android"
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

object AndroidXDependencies {
    object Versions {
        const val appCompatVersion = "1.2.0"
        const val coreVersion = "1.3.2"
        const val materialVersion = "1.2.1"
        const val constraintLayoutVersion = "2.0.4"
        const val lifecycleVersion = "2.2.0"
    }

    const val coreKtx = "androidx.core:core-ktx:${Versions.coreVersion}"
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompatVersion}"
    const val material = "com.google.android.material:material:${Versions.materialVersion}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayoutVersion}"
    const val lifecycle = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycleVersion}"

    fun all(dependencies: DependencyHandler) = dependencies.apply {
        implementation(coreKtx)
        implementation(appCompat)
        implementation(material)
        implementation(constraintLayout)
        implementation(lifecycle)
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