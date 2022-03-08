import MiscDependencies.lottie

plugins {
    id(Plugins.androidModule)
}

dependencies {
    implementation(project(Modules.ICONPACK.path))
    AndroidXDependencies.all(this)
    lottie()
}