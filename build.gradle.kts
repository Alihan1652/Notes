// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false       // Android application plugin
    alias(libs.plugins.kotlin.android) apply false            // Kotlin Android plugin
    id("androidx.navigation.safeargs") version "2.9.3" apply false
    id("org.jetbrains.kotlin.plugin.parcelize") version "1.9.23" apply false
    id("com.google.devtools.ksp") version "2.2.20-2.0.2" apply false
    id("com.google.gms.google-services") version "4.4.3" apply false
}