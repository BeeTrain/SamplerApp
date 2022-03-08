plugins {
    id(Plugins.androidModule)
}

dependencies {
    implementation(project(Modules.CORE_UI.path))
    implementation(project(Modules.CORE_DATA.path))

    AndroidXDependencies.all(this)
    DIDependencies.all(this)
}