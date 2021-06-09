import MiscDependencies.lottie
import config.setupDefaultModuleDependencies

plugins {
    id(BuildPlugins.androidLibrary)
    id(BuildPlugins.kotlinAndroid)
    id(BuildPlugins.androidModule)
}

dependencies {
    setupDefaultModuleDependencies()
    AndroidXDependencies.all(this)
    lottie()
}