plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.example.eat"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.eat"
        minSdk = 35
        targetSdk = 35
        versionCode = 1
        versionName = "1.1"

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
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.material3.window.size)
    implementation(libs.androidx.material3.adaptive.nav)
    implementation("androidx.graphics:graphics-core:1.0.3")
    implementation("androidx.navigation:navigation-runtime-ktx:2.7.7") // Последняя стабильная версия
    // Google Maps for Compose
    implementation ("org.osmdroid:osmdroid-android:6.1.20")

// This library dependencies
   // implementation ("tech.utsmankece:osm-androd-compose:${latest_version}")// Проверьте актуальную версию на Maven Central или GitHub
    // Для работы с разрешениями (Accompanist Permissions)
    implementation ("com.google.accompanist:accompanist-permissions:0.34.0")

    // Accompanist Permissions (для удобной обработки разрешений)
    implementation ("com.google.accompanist:accompanist-permissions:0.34.0") // Проверьте актуальную версию

    // Lifecycle (для запуска Location Services)
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("androidx.navigation:navigation-compose:2.7.7")
    implementation("androidx.graphics:graphics-path:1.0.1")
    implementation ("androidx.compose.material:material-icons-extended:1.6.7")
    implementation("androidx.graphics:graphics-shapes:1.1.0-alpha01")
    implementation("androidx.compose.ui:ui-text-google-fonts:1.8.1")//GoogleFonts
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.core.animation)
    implementation(libs.androidx.navigation.compose.android)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}