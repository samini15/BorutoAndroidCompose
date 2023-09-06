// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.0" apply false
    id("org.jetbrains.kotlin.android") version "1.8.10" apply false
    id("org.jetbrains.kotlin.kapt") version "1.8.10" apply false
    `kotlin-dsl`
}

buildscript {

    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath("com.android.tools.build:gradle:7.4.2")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.10")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.44")
        classpath("org.jetbrains.kotlin:kotlin-serialization:1.8.10")
    }

    /*configurations.getByName("androidTestImplementation") {
        exclude(group = "io.mockk", module = "mockk-agent-jvm")
    }*/
}