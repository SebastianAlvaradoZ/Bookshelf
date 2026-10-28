plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.example.bookshelf"
    compileSdk {
        version = release(37)
    }

    defaultConfig {
        applicationId = "com.example.bookshelf"
        minSdk = 24
        targetSdk = 37
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            optimization {
                enable = false
            }
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
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    // ViewModel integrado con Compose
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    // Retrofit + Gson: consumo de la API de Google Books
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.gson)
    // Coil: descarga y muestra imágenes de forma asíncrona
    implementation(libs.coil.compose)
    // Íconos de Material (Search, Refresh)
    implementation(libs.androidx.compose.material.icons.core)
    testImplementation(libs.junit)
    // Pruebas de corrutinas (runTest, Dispatchers.setMain)
    testImplementation(libs.kotlinx.coroutines.test)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
    debugImplementation(libs.androidx.compose.ui.tooling)
}