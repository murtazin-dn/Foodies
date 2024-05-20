plugins {
    alias(libs.plugins.foodies.android.feature)
    alias(libs.plugins.foodies.android.library.compose)
}

android {
    namespace = "com.example.designsystem"
}

dependencies {
    api(projects.core.model)
}