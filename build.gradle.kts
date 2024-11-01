// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.android.test) apply false
}

buildscript {
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://plugins.gradle.org/m2/") }
    }
}
allprojects {
    repositories {
        mavenLocal()
        google()
        mavenCentral()
    }
}