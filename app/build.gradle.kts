plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.pulsefit"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.pulsefit"
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
}

dependencies {

    // CORE
    implementation(libs.appcompat)
    implementation(libs.activity)
    implementation(libs.constraintlayout)

    // MATERIAL UI
    implementation("com.google.android.material:material:1.11.0")

    // RECYCLERVIEW
    implementation("androidx.recyclerview:recyclerview:1.3.2")

    // ROOM DATABASE
    implementation("androidx.room:room-runtime:2.6.1")
    annotationProcessor("androidx.room:room-compiler:2.6.1")

    // MPANDROIDCHART
    implementation("com.github.PhilJay:MPAndroidChart:v3.1.0")

    // TESTING
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}
