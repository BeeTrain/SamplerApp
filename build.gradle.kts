import io.gitlab.arturbosch.detekt.detekt

buildscript {
    repositories {
        google()
        jcenter()
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }

    apply {
        apply(plugin = BuildPlugins.ktlint)
        apply(plugin = BuildPlugins.detekt)
    }

    detekt {
        config = files("$rootDir/config/quality/detekt-config.yml")
    }
}

tasks.register("clean").configure {
    delete("build")
}