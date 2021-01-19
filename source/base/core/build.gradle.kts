plugins {
    id(BuildPlugins.androidLibrary)
    id(BuildPlugins.kotlinAndroid)
}

android {
    setupDefaultModuleConfig()
}

dependencies {
    setupDefaultModuleDependencies()
    implementation(AndroidXDependencies.appCompat)
    implementation(AndroidXDependencies.lifecycle)
    KotlinDependencies.coroutines(this)
}