object ApplicationConfig {
    const val applicationId = "ru.chernakov.samplerapp"

    const val compileSdkVersion = 30
    const val minSdkVersion = 21
    const val targetSdkVersion = 30

    const val buildToolsVersion = "30.0.2"

    enum class Modules(val path: String) {
        APP(":app"),

        CORE(":core"),
        CORE_UI(":core_ui")
    }
}

object BuildTypes {
    const val debug = "debug"
    const val release = "release"
}

object SourceSets {
    const val main = "main"
    const val srcMain = "src/main/kotlin"
}