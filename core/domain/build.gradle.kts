plugins {
    alias(libs.plugins.foodies.android.library)
    alias(libs.plugins.foodies.android.dagger)
}

android {
    namespace = "com.example.domain"
}

dependencies {
    api(projects.core.data)
    api(projects.core.cart)
    api(projects.core.model)
}