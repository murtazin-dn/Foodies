plugins {
    alias(libs.plugins.foodies.android.library)
    alias(libs.plugins.foodies.android.dagger)
    alias(libs.plugins.foodies.android.retrofit)
}

android {
    namespace = "com.example.network"
}

dependencies {
    api(projects.core.common)
}