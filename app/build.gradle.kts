plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.github.hemoptysisheart.sample"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.github.hemoptysisheart.sample"
        minSdk = 29
        targetSdk = 34
        versionCode = 1
        versionName = project.ext["publish.version"] as String?
        versionCode = project.ext["build.number"] as Int

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
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
        kotlinCompilerExtensionVersion = "1.5.12"
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    if (project.ext["publish.enable"] as Boolean) {
        implementation(libs.hemoptysisheart.ui.compose)
        implementation(libs.hemoptysisheart.ui.navigation)
    } else {
        implementation(project(":ui-compose"))
        implementation(project(":ui-navigation"))
    }

    implementation(libs.androidx.activity)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.layout.constraint)
    implementation(libs.androidx.lifecycle.runtime)
    implementation(libs.androidx.navigation)
    implementation(libs.hilt)

    ksp(libs.hilt.compiler)

    debugImplementation(libs.androidx.ui.test.manifest)
    debugImplementation(libs.androidx.ui.tooling)

    testImplementation(libs.kotest.runner.junit5)
    testImplementation(libs.kotlin.logging)
    testImplementation(libs.logback.classic)
    testImplementation(libs.mockk)
}
