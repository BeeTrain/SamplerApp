import AndroidXDependencies.navigation

plugins {
    id(Plugins.androidModule)
    id(Plugins.navigation)
}

dependencies {
    implementation(project(Config.Modules.APP.path))

    navigation()
    DIDependencies.all(this)
}