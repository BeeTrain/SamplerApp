import MiscDependencies.lottie
import config.setupDefaultModuleDependencies

plugins {
    id(BuildPlugins.androidModule)
}

dependencies {
    setupDefaultModuleDependencies()
    AndroidXDependencies.all(this)
    lottie()
}