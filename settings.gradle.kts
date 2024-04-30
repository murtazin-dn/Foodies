pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Foodies"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
include(":app")
include(":feature")
include(":feature:catalog")
include(":core")
include(":core:network")
include(":core:data")
