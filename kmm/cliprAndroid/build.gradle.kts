plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    namespace = "studio.zebro.clipr.android"
    compileSdk = 33
    defaultConfig {
        applicationId = "studio.zebro.clipr.android"
        minSdk = 24
        targetSdk = 33
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
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
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

    implementation(Dependencies.Koin.android)
}