import com.example.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidDaggerConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.google.devtools.ksp")
            }

            dependencies {
                "implementation"(libs.findLibrary("dagger").get())
                "implementation"(libs.findLibrary("javax.inject").get())
                "ksp"(libs.findLibrary("dagger.compiler").get())
            }

        }
    }

}