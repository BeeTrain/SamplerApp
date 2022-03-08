plugins {
    id(Plugins.androidModule)
}

dependencies {
    implementation(project(Modules.CORE_UI.path))

    // Tabs modules
    implementation(project(Modules.MAIN_FEED.path))
    implementation(project(Modules.MAIN_SERVICES.path))
    implementation(project(Modules.MAIN_PROFILE.path))

    AndroidXDependencies.all(this)
    DIDependencies.all(this)
}