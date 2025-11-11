/*
 *     TimeRangePicker/TimeRangePicker
 *     settings.gradle.kts Copyrighted by Yamin Siahmargooei at 2025/11/11
 *     settings.gradle.kts Last modified at 2025/10/20
 *     This file is part of TimeRangePicker/TimeRangePicker.
 *     Copyright (C) 2025  Yamin Siahmargooei
 *
 *     TimeRangePicker/TimeRangePicker is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     TimeRangePicker/TimeRangePicker is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with TimeRangePicker.  If not, see <https://www.gnu.org/licenses/>.
 */

pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "TimeRangePicker"
include(":app")
include(":library")
