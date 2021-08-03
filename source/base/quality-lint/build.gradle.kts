plugins {
    id(Plugins.javaLibrary)
    id(Plugins.kotlin)
    id(Plugins.kotlinJVM)
}

dependencies {
    LintDependencies.all(this)
}

java.sourceCompatibility = JavaVersion.VERSION_11
java.targetCompatibility = JavaVersion.VERSION_11

tasks {
    jar {
        manifest {
            attributes(
                mapOf("Lint-Registry-v2" to "ru.chernakov.sampler.lint.CustomLintRegistry")
            )
        }
    }
}