plugins {
    `java-gradle-plugin`
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
    jcenter()
}

val kotlinVersion = "1.4.21"
val buildToolsVersion = "4.1.0"
val navigationVersion = "2.3.2"

dependencies {
    compileOnly(gradleApi())

    implementation("com.android.tools.build:gradle:$buildToolsVersion")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
    implementation("androidx.navigation:navigation-safe-args-gradle-plugin:$navigationVersion")
}