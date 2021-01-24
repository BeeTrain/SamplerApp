import config.setupDefaultModuleDependencies

plugins {
    id(BuildPlugins.androidLibrary)
    id(BuildPlugins.kotlinAndroid)
    id(BuildPlugins.androidModule)
}

dependencies {
    setupDefaultModuleDependencies()
    implementation(AndroidXDependencies.appCompat)
    implementation(AndroidXDependencies.lifecycle)
    KotlinDependencies.coroutines(this)
}