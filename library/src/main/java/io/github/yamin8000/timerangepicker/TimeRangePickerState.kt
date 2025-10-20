package io.github.yamin8000.timerangepicker

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

    val startState = mutableFloatStateOf(initStart)
    val endState = mutableFloatStateOf(initEnd)

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
