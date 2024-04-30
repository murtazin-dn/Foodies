plugins {
    alias(libs.plugins.foodies.android.library)
    alias(libs.plugins.foodies.android.dagger)
}

android {
    namespace = "com.example.data"

}

dependencies {
    api(projects.core.network)
}