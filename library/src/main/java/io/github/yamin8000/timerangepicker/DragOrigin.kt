package io.github.yamin8000.timerangepicker

internal sealed interface DragOrigin {
    data object Start : DragOrigin
    data object End : DragOrigin
}