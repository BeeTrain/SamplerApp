import org.gradle.api.JavaVersion

const val kotlinVersion = "1.4.21"
val javaVersion = JavaVersion.VERSION_1_8

object BuildPlugins {
    const val androidApplication = "com.android.application"
    const val androidLibrary = "com.android.library"

    const val kotlinAndroid = "kotlin-android"
}

object KotlinDependencies {
    const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"
    const val stdLib = "org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion"
}

object GradleDependencies {
    object Versions {
        const val androidGradleVersion = "4.1.1"
    }

    const val androidPlugin = "com.android.tools.build:gradle:${Versions.androidGradleVersion}"
}

object AndroidXDependencies {
    object Versions {
        const val appCompatVersion = "1.2.0"
        const val coreVersion = "1.3.2"
        const val materialVersion = "1.2.1"
        const val constraintLayoutVersion = "2.0.4"
    }

    const val coreKtx = "androidx.core:core-ktx:${Versions.coreVersion}"
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompatVersion}"
    const val material = "com.google.android.material:material:${Versions.materialVersion}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayoutVersion}"
}