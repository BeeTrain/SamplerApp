plugins {
    id(Plugins.androidModule)
}

dependencies {
    implementation(project(Config.Modules.CORE_UI.path))

    // Tabs modules
    implementation(project(Config.Modules.MAIN_FEED.path))
    implementation(project(Config.Modules.MAIN_SERVICES.path))
    implementation(project(Config.Modules.MAIN_PROFILE.path))

    AndroidXDependencies.all(this)
    DIDependencies.all(this)
}