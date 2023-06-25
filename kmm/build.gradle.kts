plugins {
  //trick: for the same plugin versions in all sub-modules
  id(Dependencies.Plugin.androidApplication).version(Dependencies.Plugin.androidApplicationVersion)
    .apply(false)
  id(Dependencies.Plugin.androidLibrary).version(Dependencies.Plugin.androidLibraryVersion).apply(false)
  kotlin(Dependencies.Plugin.androidPlugin).version(Dependencies.Plugin.androidPluginVersion).apply(false)
  kotlin(Dependencies.Plugin.kmmMultiplatform).version(Dependencies.Plugin.kotlinVersion).apply(false)
  id(Dependencies.Plugin.composePlugin).version(Dependencies.Plugin.composePluginVersion).apply(false)
}

buildscript {
  dependencies {
    classpath(Dependencies.MultiplatformResources.classPathToResourcesGeneratorGradlePlugin)
  }
}

tasks.register("clean", Delete::class) {
  delete(rootProject.buildDir)
}
