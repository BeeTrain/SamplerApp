plugins {
    `java-gradle-plugin`
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
    jcenter()
    maven("https://plugins.gradle.org/m2/")
}

val kotlinVersion = "1.4.21"
val buildToolsVersion = "4.1.0"
val navigationVersion = "2.3.2"
val ktlintVersion = "9.4.1"
val detektVersion = "1.14.2"

dependencies {
    compileOnly(gradleApi())

    implementation("com.android.tools.build:gradle:$buildToolsVersion")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
    implementation("androidx.navigation:navigation-safe-args-gradle-plugin:$navigationVersion")
    implementation("org.jlleitschuh.gradle:ktlint-gradle:$ktlintVersion")
    implementation("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:$detektVersion")
}

gradlePlugin {
    plugins {
        register("android-module") {
            id = "android-module"
            implementationClass = "config.ModulePlugin"
        }
    }
}