plugins {
    id(BuildPlugins.androidLibrary)
    id(BuildPlugins.kotlinAndroid)
}

android {
    setupDefaultModuleConfig()
}

dependencies {
    implementation(project(ApplicationConfig.Modules.CORE_UI.path))
    implementation(project(ApplicationConfig.Modules.WIDGET.path))
    setupDefaultModuleDependencies()

    AndroidXDependencies.all(this)
    DIDependencies.all(this)
}