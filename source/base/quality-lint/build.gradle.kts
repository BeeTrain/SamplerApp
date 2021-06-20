plugins {
    id(BuildPlugins.javaLibrary)
    id(BuildPlugins.kotlinJVM)
}

dependencies {
    LintDependencies.all(this)
}

java.sourceCompatibility = JavaVersion.VERSION_1_8
java.targetCompatibility = JavaVersion.VERSION_1_8

tasks {
    jar {
        manifest {
            attributes(
                mapOf("Lint-Registry-v2" to "ru.chernakov.sampler.lint.CustomLintRegistry")
            )
        }
    }
}