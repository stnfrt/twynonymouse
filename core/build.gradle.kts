plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
}

android {
    compileSdkVersion(30)
    buildToolsVersion = "30.0.0"

    defaultConfig {
        minSdkVersion(19)
        targetSdkVersion(30)
        versionCode = 1
        versionName = "1.0"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    // For Kotlin projects
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    kapt {
        correctErrorTypes = true
        useBuildCache = true
    }
}

dependencies {
    val kotlinVersion = "1.3.72"
    val daggerVersion = "2.15"
    val retrofitVersion = "2.9.0"
    val constraintLayoutVersion = "1.1.3"
    val roomVersion = "2.2.5"

    val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:3.8.0"
    val okhttp = "com.squareup.okhttp3:okhttp:4.2.1"
    val okhttpSignpost = "se.akerfeldt:okhttp-signpost:1.1.0"


    api(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    api("org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion")
    api("androidx.core:core-ktx:1.3.1")
    api("androidx.appcompat:appcompat:1.2.0")
    api("androidx.constraintlayout:constraintlayout:$constraintLayoutVersion")


    //hilt di
    api("com.google.dagger:dagger-android:$daggerVersion")
    api("com.google.dagger:dagger-android-support:$daggerVersion")
    kapt("com.google.dagger:dagger-android-processor:$daggerVersion")
    kapt("com.google.dagger:dagger-compiler:$daggerVersion")

    api("com.squareup.retrofit2:retrofit:$retrofitVersion")
    api("com.squareup.retrofit2:converter-gson:$retrofitVersion")
    api(loggingInterceptor)
    api(okhttp)
    api(okhttpSignpost)

    //room
    api("androidx.room:room-runtime:$roomVersion")
    api("androidx.room:room-ktx:$roomVersion")
    kapt("androidx.room:room-compiler:$roomVersion")
}