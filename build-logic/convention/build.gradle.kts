

plugins {
    `kotlin-dsl`
}

group = "com.example.foodies.buildlogic"


dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidApplicationCompose") {
            id = "foodies.android.application.compose"
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }
        register("androidApplication") {
            id = "foodies.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidLibraryCompose") {
            id = "foodies.android.library.compose"
            implementationClass = "AndroidLibraryComposeConventionPlugin"
        }
        register("androidLibrary") {
            id = "foodies.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("androidFeature") {
            id = "foodies.android.feature"
            implementationClass = "AndroidFeatureConventionPlugin"
        }
        register("androidDagger") {
            id = "foodies.android.dagger"
            implementationClass = "AndroidDaggerConventionPlugin"
        }
        register("androidRetrofit") {
            id = "foodies.android.retrofit"
            implementationClass = "AndroidRetrofitConventionPlugin"
        }
    }
}