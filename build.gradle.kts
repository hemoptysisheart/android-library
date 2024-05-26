import java.time.Instant
import java.util.Properties

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.ksp) apply false
}

val localProperties = Properties()
localProperties.load(rootProject.projectDir.resolve("local.properties").inputStream())

subprojects {
    project.ext["version.major"] = (localProperties["version.major"] as String?)?.toInt()
        ?: 0
    project.ext["version.minor"] = (localProperties["version.minor"] as String?)?.toInt()
        ?: 0
    project.ext["version.patch"] = (localProperties["version.patch"] as String?)?.toInt()
        ?: 0
    project.ext["version.name"] = "${
        project.ext["version.major"]
    }.${project.ext["version.minor"]}.${
        project.ext["version.patch"]
    }"
    project.ext["build.number"] = System.getenv("GITHUB_RUN_NUMBER")?.toInt()
        ?: Instant.now().epochSecond.toInt()
    project.ext["publish.user"] = localProperties["publish.user"]
        ?: System.getenv("GITHUB_ACTOR")
    project.ext["publish.token"] = localProperties["publish.token"]
        ?: System.getenv("GITHUB_TOKEN")

    afterEvaluate {
        if (
            this.plugins.hasPlugin(libs.plugins.android.application.get().pluginId) ||
            this.plugins.hasPlugin(libs.plugins.android.library.get().pluginId)
        ) {
            // 하위 모듈의 단위 테스트 보고서를 프로젝트 루트의 보고서 디렉토리로 출력.
            extensions.getByType<com.android.build.gradle.BaseExtension>()
                .testOptions.unitTests.all {
                    it.useJUnitPlatform()
                    it.reports.html.outputLocation =
                        file("${rootProject.projectDir}/build/reports/${project.name}")
                }
        }
    }
}

tasks.register<Delete>("clean") {
    delete("${rootProject.projectDir}/build")
}