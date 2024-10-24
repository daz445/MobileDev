plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    kotlin("plugin.serialization") version "1.7.10"
}

android {
    namespace = "ru.dmitryzyrynov.lab15"
    compileSdk = 34

    defaultConfig {
        applicationId = "ru.dmitryzyrynov.lab15"
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
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.0") // Для сериализации
    implementation("io.ktor:ktor-client-core:2.3.0") // Основная библиотека Ktor
    implementation("io.ktor:ktor-client-cio:2.3.0") // Клиент HTTP. Можете выбрать другой, например, OKHttp.
    implementation("io.ktor:ktor-client-serialization:2.3.0")// Сериализация
    implementation("io.ktor:ktor-client-json:2.3.0") // JSON поддержка

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}