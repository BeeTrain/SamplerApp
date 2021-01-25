import org.gradle.api.JavaVersion

object ApplicationConfig {
    const val applicationId = "ru.chernakov.samplerapp"

    const val compileSdkVersion = 30
    const val minSdkVersion = 21
    const val targetSdkVersion = 30

    const val buildToolsVersion = "30.0.2"

    val javaVersion = JavaVersion.VERSION_1_8

    enum class Modules(val path: String) {
        APP(":app"),

        CORE(":core"),
        CORE_UI(":core_ui"),
        LINT(":lint"),

        SPLASH(":splash"),
        MAIN(":main"),
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