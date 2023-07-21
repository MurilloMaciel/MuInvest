@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlin)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kapt)
    alias(libs.plugins.sqldelight)
}

android {
    namespace = "maciel.murillo.muinvest"
    compileSdk = 33

    defaultConfig {
        applicationId = "maciel.murillo.muinvest"
        minSdk = 25
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        multiDexEnabled = true
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.0"
    }
    packagingOptions {
        resources {
            excludes += setOf("META-INF/AL2.0", "META-INF/LGPL2.1")
        }
    }
}

kapt {
    correctErrorTypes = true
}

hilt {
    enableAggregatingTask = true
}

sqldelight {
    databases {
        create("MuInvestDatabase") {
            packageName.set("maciel.murillo.muinvest")
//            schemaOutputDirectory.set(file("src/commonMain/sqldelight/database"))
//            verifyMigrations.set(true)
        }
    }
}

dependencies {
    implementation(libs.androidx.core)
    implementation(libs.androidx.lifecycle.runtime)

    implementation(libs.compose.activity)
    implementation(libs.compose.ui)
    implementation(libs.compose.graphics)
    implementation(libs.compose.tooling.preview)
    implementation(libs.compose.material3)

    implementation(libs.retrofit)
    implementation(libs.moshi)

    implementation(libs.hilt)
    kapt(libs.hilt.compiler)

    implementation(libs.sqldelight.coroutines.extensions)
    implementation(libs.sqldelight.android.driver)
    implementation(libs.sqldelight.runtime)

    debugImplementation(libs.compose.ui.tooling)
    debugImplementation(libs.compose.ui.test)

    testImplementation(libs.sqldelight.sqlite.driver)

    testImplementation(libs.junit)
    testImplementation(libs.androidx.junit)
    testImplementation(libs.androidx.espresso.core)
    testImplementation(libs.androidx.compose.junit)
}