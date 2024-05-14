import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

group = "com.example.foodies.buildlogic"

// Configure the build-logic plugins to target JDK 17
// This matches the JDK used to build the project, and is not related to what is running on device.
//java {
//    sourceCompatibility = JavaVersion.VERSION_17
//    targetCompatibility = JavaVersion.VERSION_17
//}
//tasks.withType<KotlinCompile>().configureEach {
//    kotlinOptions {
//        jvmTarget = JavaVersion.VERSION_17.toString()
//    }
//}

dependencies {
    compileOnly(libs.android.gradlePlugin)
//    compileOnly(libs.android.tools.common)
//    compileOnly(libs.firebase.crashlytics.gradlePlugin)
//    compileOnly(libs.firebase.performance.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
//    compileOnly(libs.ksp.gradlePlugin)
//    compileOnly(libs.room.gradlePlugin)
//    implementation(libs.truth)
}

//tasks {
//    validatePlugins {
//        enableStricterValidation = true
//        failOnWarning = true
//    }
//}
//
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
//        register("androidApplicationJacoco") {
//            id = "nowinandroid.android.application.jacoco"
//            implementationClass = "AndroidApplicationJacocoConventionPlugin"
//        }
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
//        register("androidLibraryJacoco") {
//            id = "nowinandroid.android.library.jacoco"
//            implementationClass = "AndroidLibraryJacocoConventionPlugin"
//        }
//        register("androidTest") {
//            id = "nowinandroid.android.test"
//            implementationClass = "AndroidTestConventionPlugin"
//        }
        register("androidDagger") {
            id = "foodies.android.dagger"
            implementationClass = "AndroidDaggerConventionPlugin"
        }
        register("androidRetrofit") {
            id = "foodies.android.retrofit"
            implementationClass = "AndroidRetrofitConventionPlugin"
        }
//        register("androidRoom") {
//            id = "nowinandroid.android.room"
//            implementationClass = "AndroidRoomConventionPlugin"
//        }
//        register("androidFirebase") {
//            id = "nowinandroid.android.application.firebase"
//            implementationClass = "AndroidApplicationFirebaseConventionPlugin"
//        }
//        register("androidFlavors") {
//            id = "nowinandroid.android.application.flavors"
//            implementationClass = "AndroidApplicationFlavorsConventionPlugin"
//        }
//        register("androidLint") {
//            id = "nowinandroid.android.lint"
//            implementationClass = "AndroidLintConventionPlugin"
//        }
//        register("jvmLibrary") {
//            id = "nowinandroid.jvm.library"
//            implementationClass = "JvmLibraryConventionPlugin"
//        }
    }
}