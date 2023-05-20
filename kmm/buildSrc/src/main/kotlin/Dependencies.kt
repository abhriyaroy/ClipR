object App {
  const val versionCode = 1
  const val versionName = "1.0.0"

  const val packageName = "studio.zebro.clipr"
}

object Versions {
  const val kotlin         = "1.8.10"
  const val sqlDelight     = "2.0.0-alpha05"
  const val dateTime       = "0.4.0"

  const val composeUi       = "1.4.3"
  const val composeMaterial = "1.4.3"
  const val composeActivity = "1.7.1"
  const val coil            = "1.4.0"
  const val navigation      = "2.5.1"
  const val lifecycle       = "2.6.1"

  const val material        = "1.7.0"
  const val hilt            = "2.44"
  const val hiltCompiler    = "1.0.0"

  const val coroutines  = "1.6.4"
  const val koinAndroid = "3.3.1"
  const val koinCore    = "3.3.0"
  const val ktor        = "2.2.1"

  const val coreLibraryDesugaring = "1.1.5"

  const val ktlintGradlePlugin    = "10.1.0"

  const val minSdk     = 23
  const val compileSdk = 33
  const val targetSdk  = 33
}

object Dependencies {
  const val sqlDelightAndroidDriver = "app.cash.sqldelight:android-driver:${Versions.sqlDelight}"
  const val sqlDelightNativeDriver = "app.cash.sqldelight:native-driver:${Versions.sqlDelight}"
  const val sqlDelightCommonDriver = "app.cash.sqldelight:sqlite-driver:${Versions.sqlDelight}"

  const val dateTime = "org.jetbrains.kotlinx:kotlinx-datetime:${Versions.dateTime}"

  const val hilt = "com.google.dagger:hilt-android:${Versions.hilt}"
  const val hiltDaggerCompiler = "com.google.dagger:hilt-compiler:${Versions.hilt}"
  const val hiltCompiler = "androidx.hilt:hilt-compiler:${Versions.hiltCompiler}"
  const val hiltNavigationCompose = "androidx.hilt:hilt-navigation-compose:${Versions.hiltCompiler}"

  const val composeUi = "androidx.compose.ui:ui:${Versions.composeUi}"
  const val composeUiTooling = "androidx.compose.ui:ui-tooling:${Versions.composeUi}"
  const val composeUiToolingPreview = "androidx.compose.ui:ui-tooling-preview:${Versions.composeUi}"
  const val composeUiFoundation = "androidx.compose.foundation:foundation:${Versions.composeUi}"
  const val composeMaterial = "androidx.compose.material:material:${Versions.composeMaterial}"
  const val composeActivity = "androidx.activity:activity-compose:${Versions.composeActivity}"

  const val coreLibraryDesugaring = "com.android.tools:desugar_jdk_libs:${Versions.coreLibraryDesugaring}"
}

object GradleVersions {
  const val detekt = "1.18.1"
  const val androidTools = "7.1.0-alpha12"
  const val ktLint = "10.1.0"
  const val spotless = "5.14.1"
  const val googleServices = "4.3.8"
}

object GradlePlugins {
  const val detekt = "io.gitlab.arturbosch.detekt:detekt-gradle-plugin:${GradleVersions.detekt}"
  const val googleServices = "com.google.gms:google-services:${GradleVersions.googleServices}"
  const val spotless = "com.diffplug.spotless:spotless-plugin-gradle:${GradleVersions.spotless}"
  const val ktLint = "org.jlleitschuh.gradle:ktlint-gradle:${GradleVersions.ktLint}"
  const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
  const val android = "com.android.tools.build:gradle:${GradleVersions.androidTools}"
  const val serialization = "org.jetbrains.kotlin:kotlin-serialization:${Versions.kotlin}"
  const val sqlDelight = "app.cash.sqldelight:gradle-plugin:${Versions.sqlDelight}"
  const val hilt = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt}"
}

object PluginIds {
  const val application = "com.android.application"
  const val android = "android"
  const val kotlinKapt = "kotlin-kapt"
  const val daggerHiltPlugin = "dagger.hilt.android.plugin"
  const val multiplatform = "multiplatform"
  const val androidLibrary = "com.android.library"
  const val sqlDelight = "app.cash.sqldelight"
}

object Database {
  const val databaseName = "CopiedItemsDatabase"
}