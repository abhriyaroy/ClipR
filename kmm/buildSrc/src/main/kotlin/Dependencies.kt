object App {
  const val versionCode = 1
  const val versionName = "1.0.0"

  const val packageName = "studio.zebro.clipr"
}

object Dependencies {

  object Kotlin {
    const val version = "1.8.20"
    const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"
    const val kotlinxAtomicFuVersion = "0.18.5"
    const val kotlinxAtomicFuGradlePlugin = "org.jetbrains.kotlinx:atomicfu-gradle-plugin:$kotlinxAtomicFuVersion"
  }

  object AndroidCompose {
    const val version = "1.4.0"
    const val activityComposeVersion = "1.7.0"
    const val material3Version = "1.1.0"
    const val activityCompose = "androidx.activity:activity-compose:$activityComposeVersion"
    const val ui = "androidx.compose.ui:ui:$version"
    const val uiTooling = "androidx.compose.ui:ui-tooling:$version"
    const val uiToolingPreview = "androidx.compose.ui:ui-tooling-preview:$version"
    const val foundation = "androidx.compose.foundation:foundation:$version"
    const val material = "androidx.compose.material3:material3:$material3Version"
    const val navigationComposeVersion = "2.4.0-alpha10"
    const val navigationAndroid = "androidx.navigation:navigation-compose:${navigationComposeVersion}"
  }

  object MultiplatformResources {
    const val version = "0.23.0"
    const val graphicsVersion = "0.9.0"
    const val classPathToResourcesGeneratorGradlePlugin = "dev.icerock.moko:resources-generator:$version"
    const val resourcesDependency = "dev.icerock.moko:resources:$version"
    const val resourcesComposeDependency = "dev.icerock.moko:resources-compose:$version"
    const val iosGraphics = "dev.icerock.moko:graphics:$graphicsVersion"
  }

  object SplashScreen {
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

  object RealmDb {
    const val version = "1.10.0"
    const val gradlePlugin = "io.realm.kotlin"
    const val libraryBase = "io.realm.kotlin:library-base:$version"
    const val librarySync = "io.realm.kotlin:library-sync:$version"
    const val libraryEncryption = "io.realm.kotlin:library-encryption:$version"
  }

  object DateTime {
    const val kotlinxDateTime = "org.jetbrains.kotlinx:kotlinx-datetime:0.4.0"
  }

  object PermissionsAndroid {
    const val version = "1.7.1"
    const val lib = "com.guolindev.permissionx:permissionx:$version"
  }

  object Koin {
    const val version = "3.4.0"
    const val core = "io.insert-koin:koin-core:$version"
    const val test = "io.insert-koin:koin-test:$version"
    const val android = "io.insert-koin:koin-android:$version"
    const val androidXCompose = "io.insert-koin:koin-androidx-compose:$version"
//    const val androidViewModelExtension = "io.insert-koin:koin-androidx-viewmodel:$version"
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
    const val kotlinxAtomicFu = "kotlinx-atomicfu"

    const val androidApplicationVersion = "7.4.1"
    const val androidLibraryVersion = "7.4.1"
    const val androidPluginVersion = "1.8.20"
    const val kotlinVersion = "1.8.20"
    const val composePluginVersion = "1.4.1"
  }

  object KCrypt {
    const val version = "0.0.7"
    const val kcrypt = "io.github.abhriyaroy:KCrypt:$version"
  }
}