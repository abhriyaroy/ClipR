object App {
  const val versionCode = 1
  const val versionName = "1.0.0"

  const val packageName = "studio.zebro.clipr"
}

object Dependencies {

  object Compose {
    const val version = "1.4.0"
    const val animation = "org.jetbrains.compose.animation:animation:$version"
    const val animationGraphics = "org.jetbrains.compose.animation:animation-graphics:$version"
    const val foundation = "org.jetbrains.compose.foundation:foundation:$version"
    const val material = "org.jetbrains.compose.material:material:$version"
    const val material3 = "org.jetbrains.compose.material3:material3:$version"
    const val runtime = "org.jetbrains.compose.runtime:runtime:$version"
    const val ui = "org.jetbrains.compose.ui:ui:$version"
  }

  object Plugin {
    const val kmmMultiplatform = "multiplatform"
    const val nativeCocoaPods = "native.cocoapods"
    const val androidLibrary = "com.android.library"
    const val compose = "org.jetbrains.compose"
  }
}