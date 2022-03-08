plugins {
    id(Plugins.androidModule)
}

dependencies {
    implementation(project(Modules.CORE_UI.path))

    AndroidXDependencies.all(this)
    DIDependencies.all(this)
}