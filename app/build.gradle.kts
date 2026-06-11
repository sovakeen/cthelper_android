plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
//  navigation
    alias(libs.plugins.kotlin.serialization)
//  ksp
    id("com.google.devtools.ksp")
//  hilt
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.cthelper"
    compileSdk {
        version = release(36) {
            minorApiLevel = 1
        }
    }

    defaultConfig {
        applicationId = "com.example.cthelper"
        minSdk = 29
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.material.icons.core)
    implementation(libs.androidx.compose.material.icons.extended)
    implementation(libs.androidx.compose.material3.adaptive.navigation.suite)

//  navigation
    implementation(libs.navigation.compose)
    // Views/Fragments integration
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)
    // Feature module support for Fragments
    implementation(libs.navigation.dynamic.features.fragment)
    // JSON serialization library, works with the Kotlin serialization plugin
    implementation(libs.kotlinx.serialization.json)

//  ksp
    ksp(libs.hilt.android.compiler)

//  hilt
    implementation(libs.hilt.android)
    implementation(libs.hilt.navigation.compose)

//  viewmodel
    implementation(libs.lifecycle.viewmodel)

//  network
    implementation(libs.retrofit)
    implementation(libs.retrofit.kotlinx.serialization)
    implementation(libs.okhttp)
    implementation(libs.okhttp.logging.interceptor)

//  camera
    implementation(libs.androidx.camera.camera2)
    implementation(libs.androidx.camera.lifecycle)
    implementation(libs.androidx.camera.view)

//  barcode
    implementation(libs.google.mlkit.barcode.scanning)
    implementation(libs.zxing.core)

//  accompanist
    implementation(libs.accompanist.permissions)

//  tests?
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}
