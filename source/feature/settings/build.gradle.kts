import config.setupDefaultModuleDependencies

plugins {
    id(BuildPlugins.androidModule)
}

dependencies {
    implementation(project(ApplicationConfig.Modules.APP_THEME.path))
    implementation(project(ApplicationConfig.Modules.CORE_UI.path))
    implementation(project(ApplicationConfig.Modules.CORE_DATA.path))
    setupDefaultModuleDependencies()

    AndroidXDependencies.all(this)
    DIDependencies.all(this)
}