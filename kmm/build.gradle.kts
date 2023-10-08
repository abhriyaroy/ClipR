plugins {
  id(Dependencies.Plugin.androidApplication).version(Dependencies.Plugin.androidApplicationVersion)
    .apply(false)
  id(Dependencies.Plugin.androidLibrary).version(Dependencies.Plugin.androidLibraryVersion).apply(false)
  kotlin(Dependencies.Plugin.androidPlugin).version(Dependencies.Plugin.androidPluginVersion).apply(false)
  kotlin(Dependencies.Plugin.kmmMultiplatform).version(Dependencies.Plugin.kotlinVersion).apply(false)
  id(Dependencies.Plugin.composePlugin).version(Dependencies.Plugin.composePluginVersion).apply(false)
  id(Dependencies.RealmDb.gradlePlugin).version(Dependencies.RealmDb.version).apply(false)
}

repositories {
  google()
  mavenCentral()
}

buildscript {
  dependencies {
    classpath(Dependencies.MultiplatformResources.classPathToResourcesGeneratorGradlePlugin)
    classpath(Dependencies.Kotlin.kotlinxAtomicFuGradlePlugin)
  }
}

tasks.register("clean", Delete::class) {
  delete(rootProject.buildDir)
}
