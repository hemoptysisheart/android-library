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
        val reportId = System.getenv("GITHUB_RUN_NUMBER")
            ?: "local"
        println("Report ID: $reportId")

        if (
            this.plugins.hasPlugin(libs.plugins.android.application.get().pluginId) ||
            this.plugins.hasPlugin(libs.plugins.android.library.get().pluginId)
        ) {
            // 하위 모듈의 단위 테스트 보고서를 프로젝트 루트의 보고서 디렉토리로 출력.
            extensions.getByType<com.android.build.gradle.BaseExtension>()
                .testOptions.unitTests.all {
                    it.useJUnitPlatform()
                    it.reports.html.outputLocation =
                        file("${rootProject.projectDir}/build/reports/publish/$reportId/${project.name}")
                }
        }
    }
}

tasks.register<Delete>("clean") {
    delete("${rootProject.projectDir}/build")
}
