plugins {
    id(Plugins.androidModule)
}

dependencies {
    implementation(project(Config.Modules.APP_THEME.path))
    implementation(project(Config.Modules.CORE_UI.path))
    implementation(project(Config.Modules.CORE_DATA.path))

    AndroidXDependencies.all(this)
    DIDependencies.all(this)
}