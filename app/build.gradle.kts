plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("kotlin-android")
}

android {
    compileSdkVersion(30)
    buildToolsVersion = "30.0.0"
    buildFeatures.viewBinding = true

    defaultConfig {
        applicationId = "id.twynonymouse"
        minSdkVersion(19)
        targetSdkVersion(30)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        multiDexEnabled = true
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
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

    // For Kotlin projects
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
    packagingOptions {
        exclude("META-INF/DEPENDENCIES")
        exclude("META-INF/LICENSE")
        exclude("META-INF/LICENSE.txt")
        exclude("META-INF/license.txt")
        exclude("META-INF/NOTICE")
        exclude("META-INF/NOTICE.txt")
        exclude("META-INF/notice.txt")
        exclude("META-INF/ASL2.0")
        exclude("META-INF/*.kotlin_module")
    }
}

kapt {
    correctErrorTypes = true
    useBuildCache = true
}

dependencies {
    //version
    val rainbowCakeVersion = "1.0.0"
    val coroutinesVersion = "1.3.7"
    val daggerVersion = "2.15"
    val roomVersion = "2.2.5"

    //module
    api(project(":core"))

    //rainbowcake
    api("co.zsmb:rainbow-cake-core:$rainbowCakeVersion")
    api("co.zsmb:rainbow-cake-navigation:$rainbowCakeVersion")
    api("co.zsmb:rainbow-cake-dagger:$rainbowCakeVersion")
    implementation("co.zsmb:rainbow-cake-timber:$rainbowCakeVersion")

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion")

    kapt("com.google.dagger:dagger-android-processor:$daggerVersion")
    kapt("com.google.dagger:dagger-compiler:$daggerVersion")
    api("androidx.room:room-compiler:$roomVersion")


    api("io.coil-kt:coil:0.11.0")

}