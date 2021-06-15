import config.setupDefaultModuleDependencies

plugins {
    id(BuildPlugins.androidModule)
}

dependencies {
    implementation(project(ApplicationConfig.Modules.CORE_UI.path))
    setupDefaultModuleDependencies()

    // Tabs modules
    implementation(project(ApplicationConfig.Modules.MAIN_FEED.path))
    implementation(project(ApplicationConfig.Modules.MAIN_SERVICES.path))
    implementation(project(ApplicationConfig.Modules.MAIN_PROFILE.path))

    AndroidXDependencies.all(this)
    DIDependencies.all(this)
}