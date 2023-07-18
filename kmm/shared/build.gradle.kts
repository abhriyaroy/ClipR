plugins {
    kotlin(Dependencies.Plugin.kmmMultiplatform)
    kotlin(Dependencies.Plugin.nativeCocoaPods)
    id(Dependencies.Plugin.androidLibrary)
    id(Dependencies.Plugin.composePlugin)
    id(Dependencies.Plugin.multiplatformResources)
    id(Dependencies.Plugin.sqlDelight)
}

kotlin {
    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
        sourceSets["commonMain"].resources.srcDirs(
            "src/androidMain/res"
        )
    }
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        version = "1.0"
        ios.deploymentTarget = "15.0"
        podfile = project.file("../cliprIos/Podfile")
        framework {
            baseName = "shared"
            isStatic = true
            export(Dependencies.MultiplatformResources.resourcesDependency)
            export(Dependencies.MultiplatformResources.iosGraphics)
        }
        extraSpecAttributes["resources"] = "['src/commonMain/resources/**', 'src/iosMain/resources/**']"
    }
    
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(Dependencies.Compose.runtime)
                implementation(Dependencies.Compose.animation)
                implementation(Dependencies.Compose.animationGraphics)
                implementation(Dependencies.Compose.foundation)
                implementation(Dependencies.Compose.material)
                implementation(Dependencies.Compose.material3)
                implementation(Dependencies.Compose.ui)

                implementation(Dependencies.DateTime.kotlinxDateTime)

                implementation(Dependencies.SqlDelight.runtime)
                implementation(Dependencies.SqlDelight.extensions)

                api(Dependencies.MultiplatformResources.resourcesDependency)
                api(Dependencies.MultiplatformResources.resourcesComposeDependency)
            }
        }
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }
    }
}

android {
    namespace = "studio.zebro.clipr"
    compileSdk = 33
    defaultConfig {
        minSdk = 24
    }
}

multiplatformResources {
    multiplatformResourcesPackage = "studio.zebro.clipr" // required
    multiplatformResourcesClassName = "sharedres" // optional, default MR
    iosBaseLocalizationRegion = "en" // optional, default "en"
}

