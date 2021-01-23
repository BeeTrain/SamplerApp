import extensions.getGitVersionCode
import extensions.getGitVersionName
import extensions.isGradleProjectDir

val baseModulesDirectory = "${project.rootDir}/source/base"
val featureModulesDirectory = "${project.rootDir}/source/feature"

plugins {
    id(BuildPlugins.androidApplication)
    id(BuildPlugins.kotlinAndroid)
    id(BuildPlugins.navigation)
}

android {
    compileSdkVersion(ApplicationConfig.compileSdkVersion)
    buildToolsVersion(ApplicationConfig.buildToolsVersion)

    defaultConfig {
        applicationId = ApplicationConfig.applicationId
        minSdkVersion(ApplicationConfig.minSdkVersion)
        targetSdkVersion(ApplicationConfig.targetSdkVersion)
        versionCode(getGitVersionCode())
        versionName(getGitVersionName())
    }

    compileOptions {
        sourceCompatibility = javaVersion
        targetCompatibility = javaVersion
    }

    kotlinOptions.jvmTarget = javaVersion.toString()

    lintOptions {
        disable(
            "RtlSymmetry", "RtlHardcoded", "InvalidPackage", "ParcelCreator",
            "AppLinkUrlError", "MissingTranslation", "DuplicatePlatformClasses", "CheckResult"
        )

        enable("DirectColorUse", "DirectColorInDrawableUse")
    }

    buildTypes {
        getByName(BuildTypes.debug) {
            isMinifyEnabled = false
        }

        getByName(BuildTypes.release) {
            isMinifyEnabled = true
        }
    }

    sourceSets.named(SourceSets.main).configure {
        java.srcDirs(SourceSets.srcMain)
    }

    compileOptions {
        sourceCompatibility = javaVersion
        targetCompatibility = javaVersion
    }

    kotlinOptions {
        jvmTarget = javaVersion.toString()
    }
}

dependencies {
    // Modules
    file(baseModulesDirectory).listFiles()?.forEach { baseModule ->
        if (baseModule.isDirectory && baseModule.isGradleProjectDir) {
            implementation(project(":${baseModule.name}"))
        }
    }
    file(featureModulesDirectory).listFiles()?.forEach { featureModule ->
        if (featureModule.isDirectory && featureModule.isGradleProjectDir) {
            implementation(project(":${featureModule.name}"))
        }
    }

    // Libraries
    implementation(KotlinDependencies.stdLib)
    AndroidXDependencies.all(this)
    DIDependencies.all(this)
    MiscDependencies.all(this)
}