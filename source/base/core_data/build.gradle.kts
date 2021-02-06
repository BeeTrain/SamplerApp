import config.setupDefaultModuleDependencies

plugins {
    id(BuildPlugins.androidLibrary)
    id(BuildPlugins.kotlinAndroid)
    id(BuildPlugins.androidModule)
}

dependencies {
    setupDefaultModuleDependencies()
    implementation(project(ApplicationConfig.Modules.CORE.path))
    DIDependencies.all(this)
}