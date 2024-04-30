import com.android.build.gradle.LibraryExtension
import com.example.convention.libs
import org.gradle.api.Project
import org.gradle.api.Plugin
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply("foodies.android.library")
            }
//            extensions.configure<LibraryExtension> {
////                defaultConfig {
////                    testInstrumentationRunner =
////                        "com.google.samples.apps.nowinandroid.core.testing.NiaTestRunner"
////                }
////                testOptions.animationsDisabled = true
////                configureGradleManagedDevices(this)
//            }

            dependencies {
//                add("implementation", project(":core:ui"))
//                add("implementation", project(":core:designsystem"))

//                add("implementation", libs.findLibrary("androidx.hilt.navigation.compose").get())
//                add("implementation", libs.findLibrary("androidx.lifecycle.runtimeCompose").get())
//                add("implementation", libs.findLibrary("androidx.lifecycle.viewModelCompose").get())
//                add("implementation", libs.findLibrary("androidx.tracing.ktx").get())
//
//                add("androidTestImplementation", libs.findLibrary("androidx.lifecycle.runtimeTesting").get())



            }
        }
    }
}