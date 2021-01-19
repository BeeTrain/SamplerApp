plugins {
    id(BuildPlugins.androidLibrary)
    id(BuildPlugins.kotlinAndroid)
}

android {
    setupDefaultModuleConfig()
}

dependencies {
    setupDefaultModuleDependencies()
    AndroidXDependencies.all(this)
}