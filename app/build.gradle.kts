// FILE PERBAIKAN - VERSI LIBRARY SUDAH SESUAI
// Pastikan SELURUH isi file app/build.gradle.kts Anda diganti dengan ini.

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.responsi.h1d023018"
    compileSdk = 34 // Sesuai dengan AGP 8.4.0

    defaultConfig {
        applicationId = "com.responsi.h1d023018"
        minSdk = 24
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    // Aktifkan ViewBinding (Sesuai Pertemuan 2)
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    // Dependensi Inti (format string sesuai praktikum)
    implementation("androidx.core:core-ktx:1.12.0") // Versi stabil yang kompatibel
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    // Menggunakan activity-ktx versi 1.8.2 yang kompatibel dengan compileSdk 34
    implementation("androidx.activity:activity-ktx:1.8.2")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    // ViewModel & LiveData (Sesuai Pertemuan 3)
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.7.0")

    // Retrofit & Gson (Sesuai Pertemuan 3)
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // (Opsional) Logging Interceptor - untuk debugging
    implementation("com.squareup.okhttp3:logging-interceptor:4.11.0")

    // Dependensi Uji (Test)
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}

