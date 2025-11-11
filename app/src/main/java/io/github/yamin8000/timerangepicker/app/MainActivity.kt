/*
 *     TimeRangePicker/TimeRangePicker.app.main
 *     MainActivity.kt Copyrighted by Yamin Siahmargooei at 2025/11/11
 *     MainActivity.kt Last modified at 2025/11/11
 *     This file is part of TimeRangePicker/TimeRangePicker.app.main.
 *     Copyright (C) 2025  Yamin Siahmargooei
 *
 *     TimeRangePicker/TimeRangePicker.app.main is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     TimeRangePicker/TimeRangePicker.app.main is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with TimeRangePicker.  If not, see <https://www.gnu.org/licenses/>.
 */

package io.github.yamin8000.timerangepicker.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.PreviewLightDark
import ir.yamins.timerangepicker.TimeRangePicker
import io.github.yamin8000.timerangepicker.app.ui.theme.AppTheme
import ir.yamins.timerangepicker.rememberTimeRangePickerState
import kotlin.random.Random
import kotlin.random.nextInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    content = { innerPadding ->
                        Content(modifier = Modifier.padding(innerPadding))
                    }
                )
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun Content(
    modifier: Modifier = Modifier
) {
    AppTheme(
        content = {
            Surface(
                modifier = modifier,
                content = {
                    Box(
                        content = {
                            val state = rememberTimeRangePickerState(
                                start = Random.nextInt(0..23).toFloat(),
                                end = Random.nextInt(0..23).toFloat()
                            )
                            TimeRangePicker(
                                state = state,
                                ringColor = Color(0xffFFFD55),
                                selectedArcColor = Color(0xffDE6210),
                                centerTextColor = Color.Black,
                                ringTextColor = Color.DarkGray,
                                startColor = Color(0xffF08784),
                                endColor = Color(0xffF09B59),
                            )
                        }
                    )
                }
            )
        }
    )
}