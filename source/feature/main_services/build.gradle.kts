import config.setupDefaultModuleDependencies

plugins {
    id(BuildPlugins.androidModule)
}

dependencies {
    implementation(project(ApplicationConfig.Modules.CORE_UI.path))
    setupDefaultModuleDependencies()

    AndroidXDependencies.all(this)
    DIDependencies.all(this)
}