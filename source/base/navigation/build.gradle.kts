import AndroidXDependencies.navigation
import extension.featureModulesDirectory
import extension.isGradleProjectDir

plugins {
    id(Plugins.androidModule)
    id(Plugins.navigation)
}

dependencies {
    implementation(project(Modules.CORE_UI.path))

    file(featureModulesDirectory).listFiles()?.forEach { featureModule ->
        if (featureModule.isDirectory && featureModule.isGradleProjectDir) {
            implementation(project(":${featureModule.name}"))
        }
    }

    navigation()
    DIDependencies.all(this)
}