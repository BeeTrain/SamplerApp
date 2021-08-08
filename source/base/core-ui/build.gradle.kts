import MiscDependencies.lottie

plugins {
    id(Plugins.androidModule)
}

dependencies {
    implementation(project(Config.Modules.ICONPACK.path))
    AndroidXDependencies.all(this)
    lottie()
}