plugins {
    alias(libs.plugins.foodies.android.feature)
    alias(libs.plugins.foodies.android.library.compose)
    alias(libs.plugins.foodies.android.dagger)
}

android {
    namespace = "com.example.catalog"
}

dependencies {
    api(projects.core.common)
    api(projects.core.domain)
    api(projects.core.model)

}