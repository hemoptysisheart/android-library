pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    @Suppress("UnstableApiUsage")
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)

    @Suppress("UnstableApiUsage")
    repositories {
        google()
        mavenCentral()
        maven {
            val localProperties = java.util.Properties()
            localProperties.load(rootProject.projectDir.resolve("local.properties").inputStream())

            url = uri("https://maven.pkg.github.com/hemoptysisheart/packages")
            credentials {
                username = localProperties["publish.user"] as String?
                    ?: System.getenv("GITHUB_ACTOR")
                password = localProperties["publish.token"] as String?
                    ?: System.getenv("GITHUB_TOKEN")
            }
        }
    }
}

rootProject.name = "android-library"

include(
    ":ui-state",
    ":ui-compose",
    ":state-pump",
    ":viewmodel",
    ":ui-navigation",

    ":app"
)
