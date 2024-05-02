/*
 * Copyright 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.convention

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

/**
 * Configure Compose-specific options
 */
internal fun Project.configureAndroidCompose(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
) {
    commonExtension.apply {
        buildFeatures {
            compose = true
        }

        composeOptions {
            kotlinCompilerExtensionVersion =
                libs.findVersion("androidxComposeCompiler").get().toString()
        }

        dependencies {
            val bom = libs.findLibrary("androidx-compose-bom").get()
            add("implementation", libs.findLibrary("androidx-navigation-compose").get())
            add("implementation", platform(bom))
            add("androidTestImplementation", platform(bom))
            add("implementation", libs.findLibrary("androidx-ui-tooling-preview").get())
            add("debugImplementation", libs.findLibrary("androidx-ui-tooling").get())


//            implementation(libs.androidx.junit.ktx)

//            add("implementation", libs.findLibrary("junit").get())
//            add("testImplementation", libs.findLibrary("junit").get())
//            add("androidTestImplementation", libs.findLibrary("androidx-ui-test-junit4").get())
//            testImplementation(libs.junit)
//            androidTestImplementation(libs.androidx.junit)
//            androidTestImplementation(libs.androidx.espresso.core)
//            androidTestImplementation(platform(libs.androidx.compose.bom))
//            androidTestImplementation(libs.androidx.ui.test.junit4)
//            debugImplementation(libs.androidx.ui.tooling)
//            debugImplementation(libs.androidx.ui.test.manifest)
        }
    }
}

