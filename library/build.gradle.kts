/*
 *     TimeRangePicker/TimeRangePicker.library
 *     build.gradle.kts Copyrighted by Yamin Siahmargooei at 2025/11/11
 *     build.gradle.kts Last modified at 2025/11/11
 *     This file is part of TimeRangePicker/TimeRangePicker.library.
 *     Copyright (C) 2025  Yamin Siahmargooei
 *
 *     TimeRangePicker/TimeRangePicker.library is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     TimeRangePicker/TimeRangePicker.library is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with TimeRangePicker.  If not, see <https://www.gnu.org/licenses/>.
 */

import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("com.vanniktech.maven.publish")
}

android {
    namespace = "ir.yamins.timerangepicker"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        minSdk = 24
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlin {
        compilerOptions {
            jvmTarget = JvmTarget.JVM_17
        }
    }

    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}

mavenPublishing {
    publishToMavenCentral(automaticRelease = true)
    signAllPublications()

    coordinates("ir.yamins.timerangepicker", "timerangepicker-jvm", "1.0.1")

    pom {
        name.set("TimeRangePicker")
        description.set("A Circular TimeRangePicker for Jetpack Compose on Android")
        inceptionYear.set("2025")
        url.set("https://github.com/yamin8000/TimeRangePicker")
        licenses {
            license {
                name.set("GPL-3.0 license")
                url.set("https://www.gnu.org/licenses")
                distribution.set("https://raw.githubusercontent.com/yamin8000/TimeRangePicker/master/LICENSE")
            }
        }
        developers {
            developer {
                id.set("yamin8000")
                name.set("Yamin Siahmargooei")
                email.set("me@yamins.ir")
                url.set("https://github.com/yamin8000")
            }
        }
        scm {
            url.set("https://github.com/yamin8000/TimeRangePicker")
            connection.set("scm:git:git://github.com/yamin8000/TimeRangePicker.git")
            developerConnection.set("scm:git:ssh://git@github.com/yamin8000/TimeRangePicker.git")
        }
    }
}