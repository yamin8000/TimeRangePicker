/*
 *     TimeRangePicker/TimeRangePicker.library.main
 *     TimeRangePickerState.kt Copyrighted by Yamin Siahmargooei at 2025/11/11
 *     TimeRangePickerState.kt Last modified at 2025/11/11
 *     This file is part of TimeRangePicker/TimeRangePicker.library.main.
 *     Copyright (C) 2025  Yamin Siahmargooei
 *
 *     TimeRangePicker/TimeRangePicker.library.main is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     TimeRangePicker/TimeRangePicker.library.main is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with TimeRangePicker.  If not, see <https://www.gnu.org/licenses/>.
 */

package ir.yamins.timerangepicker

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember

class TimeRangePickerState(
    initStart: Float,
    initEnd: Float
) {

    init {
        require(initStart in 0f..23f)
        require(initEnd in 0f..23f)
    }

    private val startState = mutableFloatStateOf(initStart)
    private val endState = mutableFloatStateOf(initEnd)

    var start: Float
        get() = startState.floatValue
        set(value) {
            startState.floatValue = value
        }

    var end: Float
        get() = endState.floatValue
        set(value) {
            endState.floatValue = value
        }

    override fun toString(): String {
        return "Start: $start, End: $end"
    }
}

@Composable
fun rememberTimeRangePickerState(
    start: Float,
    end: Float
): TimeRangePickerState {
    return remember {
        TimeRangePickerState(
            initStart = start,
            initEnd = end
        )
    }
}
