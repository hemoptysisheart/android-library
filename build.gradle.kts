// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.ksp) apply false
}

//subprojects {
//    afterEvaluate {
//        if (this.plugins.hasPlugin("com.android.application") || this.plugins.hasPlugin("com.android.library")) {
//            android {
//                @Suppress("UnstableApiUsage")
//                testOptions {
//                    unitTests.isReturnDefaultValues = true
//                    unitTests.all {
//                        it.useJUnitPlatform()
//                    }
//                }
//            }
//        }
//    }
//}
