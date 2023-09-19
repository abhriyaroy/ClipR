plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    namespace = "studio.zebro.clipr.android"
    compileSdk = 34
    defaultConfig {
        applicationId = "studio.zebro.clipr.android"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.6"
    }
    packagingOptions {
        resources {
//            excludes += "/META-INF/{AL2.0,LGPL2.1}"
//            excludes += "/META-INF/*.kotlin_module"
            excludes += "META-INF/DEPENDENCIES"
            excludes += "META-INF/LICENSE"
            excludes += "META-INF/LICENSE.txt"
            excludes += "META-INF/license.txt"
            excludes += "META-INF/NOTICE"
            excludes += "META-INF/NOTICE.txt"
            excludes += "META-INF/notice.txt"
            excludes += "META-INF/ASL2.0"
            excludes += "META-INF/*.kotlin_module"
            excludes += "META-INF/versions"
            excludes += "META-INF/versions/9/previous-compilation-data.bin"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(":shared"))
    implementation(Dependencies.AndroidCompose.activityCompose)
    implementation(Dependencies.AndroidCompose.ui)
    implementation(Dependencies.AndroidCompose.uiTooling)
    implementation(Dependencies.AndroidCompose.uiToolingPreview)
    implementation(Dependencies.AndroidCompose.foundation)
    implementation(Dependencies.AndroidCompose.material)
    implementation(Dependencies.SplashScreen.splashScreen)
    implementation(Dependencies.AndroidCompose.navigationAndroid)

    implementation(Dependencies.Koin.android)
    implementation(Dependencies.Koin.androidXCompose)
}