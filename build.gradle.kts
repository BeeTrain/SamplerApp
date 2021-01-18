buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath(GradleDependencies.androidPlugin)
        classpath(KotlinDependencies.gradlePlugin)
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}