import MiscDependencies.lottie

plugins {
    id(Plugins.androidModule)
}

dependencies {
    AndroidXDependencies.all(this)
    lottie()
}