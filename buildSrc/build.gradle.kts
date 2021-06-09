import org.jetbrains.kotlin.gradle.dsl.KotlinJvmCompile

plugins {
    `java-gradle-plugin`
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
    maven("https://plugins.gradle.org/m2/")
}

val kotlinVersion = "1.5.10"
val buildToolsVersion = "4.2.0"
val navigationVersion = "2.3.5"
val ktlintVersion = "10.1.0"
val detektVersion = "1.17.1"
val dependenciesVersion = "0.39.0"

dependencies {
    compileOnly(gradleApi())

    implementation("com.android.tools.build:gradle:$buildToolsVersion")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
    implementation("androidx.navigation:navigation-safe-args-gradle-plugin:$navigationVersion")
    implementation("org.jlleitschuh.gradle:ktlint-gradle:$ktlintVersion")
    implementation("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:$detektVersion")
    implementation("com.github.ben-manes:gradle-versions-plugin:$dependenciesVersion")
}

gradlePlugin {
    plugins {
        register("android-module") {
            id = "android-module"
            implementationClass = "config.ModulePlugin"
        }
    }
}

tasks.withType(KotlinJvmCompile::class) {
    kotlinOptions.useIR = true
}