plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp.plug.module)
    alias(libs.plugins.hilt.android)
}

android {
    namespace = "com.example.horseracing"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.horseracing"
        minSdk = 24
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
    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        viewBinding = true
    }

    hilt {
        enableAggregatingTask = false
    }
}

    dependencies {
        ksp(libs.hilt.compiler)
        ksp(libs.hilt.androidx.compiler)
        ksp(libs.room.compiler)
        implementation(libs.room.runtime)
        implementation(libs.room.ktx)
        implementation(libs.androidx.recyclerview)
        implementation(libs.hilt.android)
        implementation(libs.androidx.fragment.ktx)
        implementation(libs.androidx.core.ktx)
        implementation(libs.androidx.appcompat)
        implementation(libs.material)
        implementation(libs.androidx.activity)
        implementation(libs.androidx.constraintlayout)
        implementation(libs.kotlinx.coroutines.core)
        implementation(libs.kotlinx.coroutines.android)
        implementation(libs.lifecycle.viewmodel.ktx)
        implementation(libs.lifecycle.runtime.ktx)
        implementation(libs.androidx.viewpager2)
        implementation(libs.androidx.cardview)
        implementation(libs.gson)
        testImplementation(libs.junit)
        androidTestImplementation(libs.androidx.junit)
        androidTestImplementation(libs.androidx.espresso.core)
}