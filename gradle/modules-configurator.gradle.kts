/** Attach modules **/
attachAllModules()

fun attachAllModules() {
    attachCompositeBuildModules()
    attachProjectModules()
}

fun attachCompositeBuildModules() {
    var compositeModulesCount = 0
    println("attach composite build modules...")
    rootDir.walk()
        .maxDepth(5)
        .filter { it.isGradleModule && it.isCompositeBuildModule }
        .forEach {
            it.attachCompositeBuildModule()
            compositeModulesCount++
        }
    println("$compositeModulesCount composite module(s) attached.\n")
}

fun attachProjectModules() {
    var projectModulesCount = 0
    println("attach project modules...")
    rootDir.walk()
        .maxDepth(5)
        .filter { it.isGradleModule && it.isCompositeBuildModule.not() }
        .forEach {
            it.attachModule()
            projectModulesCount++
        }
    println("$projectModulesCount project module(s) attached.\n")
}

fun File.attachModule() {
    val moduleName = ":$name"
    include(moduleName)
    project(moduleName).projectDir = file(path)
    println("project module \"$moduleName\" attached")
}

fun File.attachCompositeBuildModule() {
    val moduleName = ":$name"
    includeBuild(absolutePath)
    println("composite module \"$moduleName\" attached")
}

val File.isCompositeBuildModule: Boolean
    get() = name.contains("build")

val File.isGradleModule: Boolean
    get() {
        return name != "buildSrc" &&
            isDirectory &&
            absolutePath != rootDir.path &&
            isBuildGradleConfigExists
    }

val File.isBuildGradleConfigExists: Boolean
    get() {
        return file("$absolutePath/build.gradle.kts").exists() ||
            file("$absolutePath/build.gradle").exists()
    }