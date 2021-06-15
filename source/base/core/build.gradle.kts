import KotlinDependencies.coroutines
import config.setupDefaultModuleDependencies

plugins {
    id(BuildPlugins.androidModule)
}

dependencies {
    setupDefaultModuleDependencies()
    implementation(AndroidXDependencies.appCompat)
    implementation(AndroidXDependencies.lifecycle)
    coroutines()
}