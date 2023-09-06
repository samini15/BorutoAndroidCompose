plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("kotlin-android")
    id("dagger.hilt.android.plugin")
    id("kotlinx-serialization")
}

android {
    namespace = "com.example.borutoandroidcompose"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.borutoandroidcompose"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        //testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        testInstrumentationRunner = "com.example.borutoandroidcompose.testSetup.HiltTestRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.10.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    implementation("androidx.activity:activity-compose:1.7.2")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.material:material:1.6.0-alpha03")
    implementation("androidx.navigation:navigation-compose:2.6.0")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    androidTestImplementation("com.google.truth:truth:1.0.1")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    // Compose Lifecycle
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.7.0-alpha01")

    // Room --> https://developer.android.com/training/data-storage/room?hl=fr
    implementation("androidx.room:room-runtime:2.5.2")
    implementation("androidx.room:room-ktx:2.5.2")
    implementation("androidx.room:room-paging:2.5.2")
    annotationProcessor("androidx.room:room-compiler:2.5.2")
    kapt("androidx.room:room-compiler:2.5.2")


    // Dagger - Hilt
    implementation("com.google.dagger:hilt-android:2.44.2")
    implementation("androidx.hilt:hilt-navigation-compose:1.1.0-alpha01")
    kapt("com.google.dagger:hilt-android-compiler:2.44")
    kapt("androidx.hilt:hilt-compiler:1.0.0")

    // Accompanist Pager indicator - ONLY for Indicator -- DEPRECATED
    implementation("com.google.accompanist:accompanist-pager-indicators:0.21.2-beta")

    // Data store preferences
    implementation("androidx.datastore:datastore-preferences:1.0.0")

    // Kotlin serialization
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:1.0.0")

    // Paging
    implementation("androidx.paging:paging-runtime-ktx:3.2.0")
    implementation("androidx.paging:paging-compose:3.2.0")

    // Coil image loading
    implementation("io.coil-kt:coil-compose:2.4.0")

    // Palette API for color extraction
    implementation("androidx.palette:palette-ktx:1.0.0")

    implementation("com.google.accompanist:accompanist-systemuicontroller:0.33.0-alpha")

    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.1")
    testImplementation("com.google.truth:truth:1.0.1")
    testImplementation("com.squareup.okhttp3:mockwebserver:4.11.0")

    androidTestImplementation("com.willowtreeapps.assertk:assertk:0.26.1")
    androidTestImplementation("io.mockk:mockk-android:1.12.5")
    androidTestImplementation("com.google.dagger:hilt-android-testing:2.44")
    kaptAndroidTest("com.google.dagger:hilt-android-compiler:2.44")
}