package internal

import java.io.File

internal val File.isGradleProjectDir
    get() = listFiles()?.any { it.name == "build.gradle" || it.name == "build.gradle.kts" } ?: false