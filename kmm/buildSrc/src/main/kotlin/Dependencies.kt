object App {
  const val versionCode = 1
  const val versionName = "1.0.0"

  const val packageName = "studio.zebro.clipr"
}

object Dependencies {

  object Kotlin {
    const val version = "1.8.20"
    const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"
  }

  object AndroidCompose {
    const val version = "1.4.0"
    const val activityComposeVersion = "1.7.0"
    const val activityCompose = "androidx.activity:activity-compose:$activityComposeVersion"
    const val ui = "androidx.compose.ui:ui:$version"
    const val uiTooling = "androidx.compose.ui:ui-tooling:$version"
    const val uiToolingPreview = "androidx.compose.ui:ui-tooling-preview:$version"
    const val foundation = "androidx.compose.foundation:foundation:$version"
    const val material = "androidx.compose.material:material:$version"
  }

  object MultiplatformResources {
    const val version = "0.23.0"
    const val graphicsVersion = "0.9.0"
    const val classPathToResourcesGeneratorGradlePlugin = "dev.icerock.moko:resources-generator:$version"
    const val resourcesDependency = "dev.icerock.moko:resources:$version"
    const val resourcesComposeDependency = "dev.icerock.moko:resources-compose:$version"
    const val iosGraphics = "dev.icerock.moko:graphics:$graphicsVersion"
  }

  object Splashscreen {
    private const val version = "1.0.0"
    val splashScreen = "androidx.core:core-splashscreen:$version"
  }

  object Compose {
    const val version = "1.4.1"
    const val animation = "org.jetbrains.compose.animation:animation:$version"
    const val animationGraphics = "org.jetbrains.compose.animation:animation-graphics:$version"
    const val foundation = "org.jetbrains.compose.foundation:foundation:$version"
    const val material = "org.jetbrains.compose.material:material:$version"
    const val material3 = "org.jetbrains.compose.material3:material3:$version"
    const val runtime = "org.jetbrains.compose.runtime:runtime:$version"
    const val ui = "org.jetbrains.compose.ui:ui:$version"
  }

  object SqlDelight {
    const val version = "1.5.5"
    const val runtime = "com.squareup.sqldelight:runtime:$version"
    const val extensions = "com.squareup.sqldelight:coroutines-extensions:$version"
    const val androidDriver = "com.squareup.sqldelight:android-driver:$version"
    const val nativeDriver = "com.squareup.sqldelight:native-driver:$version"
    const val gradlePlugin = "com.squareup.sqldelight:gradle-plugin:$version"
  }

  object DateTime {
    const val kotlinxDateTime = "org.jetbrains.kotlinx:kotlinx-datetime:0.4.0"
  }

  object Plugin {
    const val kmmMultiplatform = "multiplatform"
    const val nativeCocoaPods = "native.cocoapods"
    const val androidLibrary = "com.android.library"
    const val androidApplication = "com.android.application"
    const val androidPlugin = "android"
    const val composePlugin = "org.jetbrains.compose"
    const val multiplatformResources = "dev.icerock.mobile.multiplatform-resources"
    const val sqlDelight = "com.squareup.sqldelight"

    const val androidApplicationVersion = "7.4.1"
    const val androidLibraryVersion = "7.4.1"
    const val androidPluginVersion = "1.8.20"
    const val kotlinVersion = "1.8.20"
    const val composePluginVersion = "1.4.1"
  }
}