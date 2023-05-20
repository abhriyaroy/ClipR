plugins {
    id(PluginIds.application)
    kotlin(PluginIds.android)
    id(PluginIds.kotlinKapt)
    id(PluginIds.daggerHiltPlugin)
}
apply {
    from("../ktlint.gradle")
}

android {
    namespace = App.packageName
    compileSdk = Versions.compileSdk
    defaultConfig {
        applicationId = App.packageName
        minSdk = Versions.minSdk
        targetSdk = Versions.targetSdk
        versionCode = App.versionCode
        versionName = App.versionName
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.4"
    }
    compileOptions {
        isCoreLibraryDesugaringEnabled = true
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
    implementation(Dependencies.composeUi)
    implementation(Dependencies.composeUiTooling)
    implementation(Dependencies.composeUiToolingPreview)
    implementation(Dependencies.composeUiFoundation)
    implementation(Dependencies.composeMaterial)
    implementation(Dependencies.composeActivity)

    coreLibraryDesugaring(Dependencies.coreLibraryDesugaring)

    implementation(Dependencies.hilt)
    kapt(Dependencies.hiltDaggerCompiler)
    kapt(Dependencies.hiltCompiler)
    implementation(Dependencies.hiltNavigationCompose)

    implementation(Dependencies.dateTime)
}