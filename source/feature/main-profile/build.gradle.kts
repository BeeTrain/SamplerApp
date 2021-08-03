plugins {
    id(Plugins.androidLibrary)
    id(Plugins.androidKotlin)
    id(Plugins.androidModule)
}

dependencies {
    implementation(project(Config.Modules.CORE_UI.path))

    AndroidXDependencies.all(this)
    DIDependencies.all(this)
}