plugins {
  `kotlin-dsl`
}

repositories {
  jcenter()
  maven("https://dl.bintray.com/kotlin/kotlin-eap")
  google()
  mavenCentral()
}

//val compileKotlin: KotlinCompile by tasks
//compileKotlin.kotlinOptions {
//  jvmTarget = "1.8"
//}
//val compileTestKotlin: KotlinCompile by tasks
//compileTestKotlin.kotlinOptions {
//  jvmTarget = "1.8"
//}