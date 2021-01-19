package extensions

import java.io.File

val File.isGradleProjectDir
    get() = listFiles()?.any { it.name == "build.gradle" || it.name == "build.gradle.kts" } ?: false