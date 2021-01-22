/* Project Settings */

// Attach project modules
rootDir
    .walk()
    .maxDepth(5)
    .filter {
        it.name != "buildSrc"
                && it.isDirectory
                && it.absolutePath != rootDir.path
                && (file("${it.absolutePath}/build.gradle.kts").exists()
                || file("${it.absolutePath}/build.gradle").exists())
    }
    .forEach {
        val moduleName = ":${it.name}"
        include(moduleName)
        project(moduleName).projectDir = file(it.path)
    }

// Lock dynamic dependency versions
buildscript {
    configurations.classpath {
        resolutionStrategy.activateDependencyLocking()
    }
}