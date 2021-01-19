import com.android.build.gradle.LibraryExtension
import org.gradle.api.artifacts.dsl.DependencyHandler

fun LibraryExtension.setupDefaultModuleConfig() = apply {
    compileSdkVersion(ApplicationConfig.compileSdkVersion)

    defaultConfig {
        minSdkVersion(ApplicationConfig.minSdkVersion)
        targetSdkVersion(ApplicationConfig.targetSdkVersion)
    }

    buildTypes.getByName(BuildTypes.debug) {
        isDebuggable = true
        isDefault = true
    }

    buildTypes.getByName(BuildTypes.release) {
        isDebuggable = false
    }

    sourceSets.named(SourceSets.main).configure {
        java.srcDirs(SourceSets.srcMain)
    }

    compileOptions {
        sourceCompatibility = javaVersion
        targetCompatibility = javaVersion
    }
}

fun DependencyHandler.setupDefaultModuleDependencies() {
    MiscDependencies.timber(this)
    KotlinDependencies.defaultModuleLibs(this)
}