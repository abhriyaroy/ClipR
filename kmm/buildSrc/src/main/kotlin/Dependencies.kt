object App {
  const val versionCode = 1
  const val versionName = "1.0.0"
}

object Versions {
  const val kotlin         = "1.8.10"
  const val sqlDelight     = "2.0.0-alpha05"
  const val dateTime       = "0.4.0"

  const val composeUi       = "1.3.2"
  const val composeMaterial = "1.3.1"
  const val coil            = "1.4.0"
  const val activityCompose = "1.6.1"
  const val navigation      = "2.5.1"
  const val lifecycle       = "2.6.1"

  const val material        = "1.7.0"

  const val coroutines  = "1.6.4"
  const val koinAndroid = "3.3.1"
  const val koinCore    = "3.3.0"
  const val ktor        = "2.2.1"

  const val minSdk     = 23
  const val compileSdk = 33
  const val targetSdk  = 33
}

object Dependencies {


  const val sqlDelightAndroidDriver = "app.cash.sqldelight:android-driver:${Versions.sqlDelight}"
  const val sqlDelightNativeDriver = "app.cash.sqldelight:native-driver:${Versions.sqlDelight}"
  const val sqlDelightCommonDriver = "app.cash.sqldelight:sqlite-driver:${Versions.sqlDelight}"

  const val dateTime = "org.jetbrains.kotlinx:kotlinx-datetime:${Versions.dateTime}"
}