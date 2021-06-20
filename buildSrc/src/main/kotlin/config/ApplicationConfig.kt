import org.gradle.api.JavaVersion

@Suppress("unused")
object ApplicationConfig {
    const val applicationId = "ru.chernakov.sampler"

    const val compileSdkVersion = 30
    const val minSdkVersion = 21
    const val targetSdkVersion = 30

    const val buildToolsVersion = "30.0.2"

    val javaVersion = JavaVersion.VERSION_1_8

    enum class Modules(val path: String) {
        APP(":app"),
        APP_THEME(":app-theme"),

        CORE(":core"),
        CORE_DATA(":core-data"),
        CORE_UI(":core-ui"),
        LINT(":quality-lint"),

        SPLASH(":splash"),
        MAIN(":main"),

        MAIN_FEED(":main-feed"),
        MAIN_SERVICES(":main-services"),
        MAIN_PROFILE(":main-profile"),
        SETTINGS(":settings"),
        SWIPER(":swiper"),
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