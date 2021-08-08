@Suppress("unused")
object Config {
    const val applicationId = "ru.chernakov.sampler"

    const val compileSdkVersion = 30
    const val minSdkVersion = 21
    const val targetSdkVersion = 30

    const val buildToolsVersion = "30.0.2"

    enum class Modules(val path: String) {
        APP(":app"),
        APP_THEME(":app-theme"),

        CORE(":core"),
        CORE_DATA(":core-data"),
        CORE_UI(":core-ui"),
        ICONPACK(":iconpack"),
        LINT(":quality-lint"),

        SPLASH(":splash"),
        MAIN(":main"),

        MAIN_FEED(":main-feed"),
        MAIN_SERVICES(":main-services"),
        MAIN_PROFILE(":main-profile"),
        SETTINGS(":settings"),
        SWIPER(":swiper"),
        LOGBOOK(":logbook")
    }
}

object BuildTypes {
    const val debug = "debug"
    const val release = "release"
}

enum class SourceSets(val title: String, val path: String) {
    MAIN(title = "main", path = "src/main/kotlin")
}