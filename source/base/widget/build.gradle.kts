plugins {
    id(BuildPlugins.androidLibrary)
    id(BuildPlugins.kotlinAndroid)
}

android {
    setupDefaultModuleConfig()
}

dependencies {
    implementation(project(ApplicationConfig.Modules.CORE_UI.path))
    setupDefaultModuleDependencies()
    AndroidXDependencies.all(this)
}