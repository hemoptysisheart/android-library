plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.github.hemoptysisheart.viewmodel"
    compileSdk = 34

    defaultConfig {
        minSdk = 29

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    if (project.ext["publish.enable"] as Boolean) {
        api("com.github.hemoptysisheart.android:ui-compose:${project.ext["publish.version"]}")
        api("com.github.hemoptysisheart.android:state-pump:${project.ext["publish.version"]}")
    } else {
        api(project(":ui-compose"))
        api(project(":state-pump"))
    }

    implementation(libs.androidx.lifecycle.common)
    implementation(libs.androidx.lifecycle.viewmodel)
    implementation(libs.javax.inject)

    testImplementation(libs.kotest.runner.junit5)
    testImplementation(libs.kotlin.logging)
    testImplementation(libs.logback.classic)
    testImplementation(libs.mockk)
}
