import KotlinDependencies.coroutines

plugins {
    id(Plugins.androidModule)
}

dependencies {
    implementation(AndroidXDependencies.appCompat)
    implementation(AndroidXDependencies.lifecycle)
    coroutines()
}