/// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript{


    var kotlin_version = "1.8.0"
    var nav_version = "2.5.3"
    var hilt_version = "2.45"

    repositories{
        google()
        mavenCentral()
    }
    dependencies{
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:$nav_version")
        classpath("com.google.dagger:hilt-android-gradle-plugin:$hilt_version")
    }
}

plugins {
    id("com.android.application") version "8.1.0-rc01" apply false
    id("org.jetbrains.kotlin.android") version "1.8.10" apply false
    id("com.android.library") version "7.3.1" apply false
}