import config.setupDefaultModuleDependencies

plugins {
    id(BuildPlugins.androidLibrary)
    id(BuildPlugins.kotlinAndroid)
    id(BuildPlugins.androidModule)
}

dependencies {
    implementation(project(ApplicationConfig.Modules.CORE_UI.path))
    setupDefaultModuleDependencies()

    AndroidXDependencies.all(this)
    DIDependencies.all(this)
}