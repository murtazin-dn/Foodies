import com.example.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidRetrofitConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("kotlinx-serialization")
            }

            dependencies {
                "implementation"(libs.findLibrary("retrofit.core").get())
                "implementation"(libs.findLibrary("retrofit.kotlinx.serialization").get())
                "implementation"(libs.findLibrary("kotlinx.serialization.json").get())
                "implementation"(libs.findLibrary("okhttp.logging").get())
            }

        }
    }

}