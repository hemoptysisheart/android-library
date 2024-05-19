// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.ksp) apply false
}

subprojects {
    afterEvaluate {
        if (
            this.plugins.hasPlugin(libs.plugins.android.application.get().pluginId) ||
            this.plugins.hasPlugin(libs.plugins.android.library.get().pluginId)
        ) {
            extensions.getByType<com.android.build.gradle.BaseExtension>()
                .testOptions.unitTests.all {
                    it.useJUnitPlatform()
                    it.reports.html.outputLocation =
                        file("${rootProject.projectDir}/build/reports/tests/${project.name}")
                }
        }
    }
}
