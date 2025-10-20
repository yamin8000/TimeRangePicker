package io.github.yamin8000.timerangepicker.utils

import androidx.compose.ui.geometry.Offset
import kotlin.math.sqrt

internal object Utility {
    fun Offset.euclideanDistance(other: Offset): Float {
        return sqrt((this.x - other.x) * (this.x - other.x) + (this.y - other.y) * (this.y - other.y))
    }
}