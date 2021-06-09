import AndroidXDependencies.navigation
import config.setupDefaultModuleDependencies
import extensions.featureModulesDirectory
import extensions.isGradleProjectDir

plugins {
    id(BuildPlugins.androidLibrary)
    id(BuildPlugins.kotlinAndroid)
    id(BuildPlugins.androidModule)
    id(BuildPlugins.navigation)
}

dependencies {
    setupDefaultModuleDependencies()
    implementation(project(ApplicationConfig.Modules.CORE_UI.path))

    file(featureModulesDirectory).listFiles()?.forEach { featureModule ->
        if (featureModule.isDirectory && featureModule.isGradleProjectDir) {
            implementation(project(":${featureModule.name}"))
        }
    }

    navigation()
    DIDependencies.all(this)
}