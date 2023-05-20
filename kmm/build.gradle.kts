plugins {
    //trick: for the same plugin versions in all sub-modules
    id("com.android.application").version("7.4.1").apply(false)
    id("com.android.library").version("7.4.1").apply(false)
    kotlin("android").version("1.8.10").apply(false)
    kotlin("multiplatform").version("1.8.10").apply(false)
}

buildscript {
    dependencies {
        classpath(GradlePlugins.sqlDelight)
        classpath(GradlePlugins.hilt)
        classpath(GradlePlugins.ktLint)
    }
}

allprojects {
  tasks.register("installGitHook", Copy::class) {
    from(File(rootProject.rootDir, "pre-commit"))
    into(File(rootProject.rootDir, ".git/hooks"))
    fileMode = 777
  }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

tasks.getByPath(":shared:preBuild").dependsOn("installGitHook")
tasks.getByPath(":ClipR:preBuild").dependsOn("installGitHook")