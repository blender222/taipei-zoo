// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    extra.apply {
        set("nav_version", "2.7.7")
    }
    dependencies {
        // Safe Args
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:${rootProject.extra["nav_version"]}")
    }
}

plugins {
    id("com.android.application") version "8.2.2" apply false
    id("org.jetbrains.kotlin.android") version "1.9.22" apply false

    // KSP
    id("com.google.devtools.ksp") version "1.9.0-1.0.13" apply false

    // Hilt
    id("com.google.dagger.hilt.android") version "2.49" apply false
}