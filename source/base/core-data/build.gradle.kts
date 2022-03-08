plugins {
    id(Plugins.androidModule)
}

dependencies {
    implementation(project(Modules.CORE.path))
    DIDependencies.all(this)
}