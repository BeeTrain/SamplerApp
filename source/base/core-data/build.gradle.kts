plugins {
    id(Plugins.androidModule)
}

dependencies {
    implementation(project(Config.Modules.CORE.path))
    DIDependencies.all(this)
}