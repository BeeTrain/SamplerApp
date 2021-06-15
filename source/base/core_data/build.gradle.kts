import config.setupDefaultModuleDependencies

plugins {
    id(BuildPlugins.androidModule)
}

dependencies {
    setupDefaultModuleDependencies()
    implementation(project(ApplicationConfig.Modules.CORE.path))
    DIDependencies.all(this)
}