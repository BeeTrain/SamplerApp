import extensions.getGitVersionCode
import extensions.getGitVersionName

plugins {
    id(BuildPlugins.androidApplication)
    id(BuildPlugins.kotlinAndroid)
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

    buildTypes {
        getByName(BuildTypes.release) {
            isMinifyEnabled = false
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
    implementation(KotlinDependencies.stdLib)
    implementation(AndroidXDependencies.coreKtx)
    implementation(AndroidXDependencies.appCompat)
    implementation(AndroidXDependencies.material)
    implementation(AndroidXDependencies.constraintLayout)
}