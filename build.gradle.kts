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
    apply(plugin = "maven-publish")

    project.ext["publish.enable"] = (localProperties["publish.enable"] as String?)?.toBoolean()
        ?: false
    project.ext["publish.version"] = localProperties["publish.version"]
        ?: "0.0.1"
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

        if (this.plugins.hasPlugin(libs.plugins.android.library.get().pluginId)) {
            extensions.getByType<PublishingExtension>().run {
                publications {
                    create<MavenPublication>("GitHubPackages") {
                        groupId = "com.github.hemoptysisheart.android"
                        artifactId = project.name
                        version = project.ext["publish.version"] as String

                        afterEvaluate {
                            from(components["release"])
                        }
                    }
                }

                repositories {
                    maven {
                        name = "GitHubPackages"
                        url = uri("https://maven.pkg.github.com/hemoptysisheart/packages")
                        credentials {
                            username = project.ext["publish.user"] as String?
                            password = project.ext["publish.token"] as String?
                        }
                    }
                }
            }
        }
    }
}

tasks.register<Delete>("clean") {
    delete("${rootProject.projectDir}/build")
}
