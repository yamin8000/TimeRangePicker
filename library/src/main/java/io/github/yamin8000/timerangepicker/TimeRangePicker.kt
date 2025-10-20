package io.github.yamin8000.timerangepicker

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.sp
import io.github.yamin8000.timerangepicker.utils.Utility.euclideanDistance
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.sin

private const val QUANT = 360 / 24f
private const val FONT_SCALE = 50f
private const val PADDING_SCALE = 40

@Composable
fun TimeRangePicker(
    state: TimeRangePickerState,
    modifier: Modifier = Modifier,
    ringColor: Color = MaterialTheme.colorScheme.surfaceVariant,
    selectedArcColor: Color = MaterialTheme.colorScheme.primary,
    centerTextColor: Color = MaterialTheme.colorScheme.onSurface,
    ringTextColor: Color = MaterialTheme.colorScheme.onSurface,
    startColor: Color = MaterialTheme.colorScheme.onPrimary,
    endColor: Color = MaterialTheme.colorScheme.onPrimary,
) {
    CompositionLocalProvider(
        value = LocalLayoutDirection provides LayoutDirection.Ltr,
        content = {
            val density = LocalDensity.current
            val window = LocalWindowInfo.current
            val padding = remember(window, density) {
                with(density) { (window.containerSize.width / PADDING_SCALE).toDp() }
            }

            var points by remember { mutableStateOf(mapOf<Float, Offset>()) }
            var dragOrigin by remember { mutableStateOf<DragOrigin?>(null) }

            val measurer = rememberTextMeasurer()

            val touchArea = remember(window) { window.containerSize.width / QUANT * 1.5f }

            Canvas(
                modifier = modifier
                    .aspectRatio(1f)
                    .padding(padding)
                    .pointerInput(Unit) {
                        detectDragGestures(
                            onDragStart = { dragStartingPoint ->
                                val startPoint = points[state.start]
                                val endPoint = points[state.end]
                                if (startPoint != null && endPoint != null) {
                                    val start = dragStartingPoint.euclideanDistance(
                                        startPoint
                                    )
                                    val end = dragStartingPoint.euclideanDistance(
                                        endPoint
                                    )

                                    dragOrigin = if (start < end && start < touchArea) {
                                        DragOrigin.Start
                                    } else if (end < start && end < touchArea) {
                                        DragOrigin.End
                                    } else if (start - end < touchArea / 2) {
                                        DragOrigin.Start
                                    } else {
                                        null
                                    }
                                }
                            },
                            onDrag = { change, drag ->
                                val near = points.minByOrNull { p ->
                                    p.value.euclideanDistance(change.position)
                                }
                                if (near != null) {
                                    when (dragOrigin) {
                                        DragOrigin.End -> {
                                            state.end = near.key
                                        }

                                        DragOrigin.Start -> {
                                            state.start = near.key
                                        }

                                        null -> {}
                                    }
                                }
                            }
                        )
                    },
                onDraw = {
                    val fontSize = size.minDimension / FONT_SCALE

                    val ringWidth = size.minDimension / QUANT
                    val ringSize = (size.minDimension / 2f) - (ringWidth * 2) + fontSize

                    drawCircle(
                        color = ringColor,
                        style = Stroke(ringWidth),
                        radius = ringSize
                    )

                    val startText = if (state.start % 1 == 0f) {
                        "${state.start.toInt()}:00"
                    } else "${state.start.toInt()}:30"
                    val endText = if (state.end % 1 == 0f) {
                        "${state.end.toInt()}:00"
                    } else "${state.end.toInt()}:30"
                    val centerText = "$startText\n$endText"
                    val centerTextStyle = TextStyle.Default.copy(
                        color = centerTextColor,
                        fontSize = fontSize.sp,
                        textAlign = TextAlign.Center
                    )
                    val centerTextSize = measurer.measure(
                        text = centerText,
                        style = centerTextStyle,
                    )
                    drawText(
                        textMeasurer = measurer,
                        text = centerText,
                        style = centerTextStyle,
                        topLeft = size.center.minus(
                            Offset(
                                centerTextSize.size.width / 2f,
                                centerTextSize.size.height / 2f
                            )
                        )
                    )
                    /**
                     * When drawing an Arc using Canvas, somehow, 0 degrees angle represents a
                     * 3 o'clock of a real physical 12 hour clock, but our component's zero is
                     * on 12 o'clock of a real clock so we need a minus 6 hour offset
                     */
                    val startAngle = (state.start - 6) * QUANT
                    val endAngle = if (state.start < state.end) {
                        ((state.end - 6) * QUANT) - startAngle
                    } else {
                        ((state.end - 6 + 24) * QUANT) - startAngle
                    }

                    val arcSize = Size(ringSize * 2, ringSize * 2)
                    val arcOffset = Offset(ringWidth * 2 - fontSize, ringWidth * 2 - fontSize)
                    drawArc(
                        color = selectedArcColor.copy(alpha = .25f),
                        startAngle = startAngle,
                        sweepAngle = endAngle,
                        useCenter = false,
                        size = arcSize,
                        topLeft = arcOffset,
                        style = Stroke(
                            width = ringWidth * 1.2f,
                            cap = StrokeCap.Round
                        )
                    )
                    drawArc(
                        color = selectedArcColor,
                        startAngle = startAngle,
                        sweepAngle = endAngle,
                        useCenter = false,
                        size = arcSize,
                        topLeft = arcOffset,
                        style = Stroke(
                            width = ringWidth,
                            cap = StrokeCap.Round
                        )
                    )

                    //starting at 0 o'clock of a real clock
                    var angle = -90.0
                    //-90 + 360 = 270 (a period)
                    while (angle < 270) {
                        val isHour = angle.toInt() % QUANT.toInt() == 0
                        val isHalf = angle % (QUANT / 2) == 0.0 && !isHour
                        val radians = Math.toRadians(angle)
                        val hour = ((angle / QUANT) + 6).toFloat()

                        if (isHour) {
                            val text = hour.toInt().toString()
                            val textSize = measurer.measure(text)

                            val width = (size.width / 2 - textSize.size.width / 2)
                            val textX = cos(radians) * width + size.center.x - padding.value
                            val height = (size.height / 2 - textSize.size.height / 2)
                            val textY = sin(radians) * height + size.center.y - padding.value

                            val textColor = if (state.start < state.end) {
                                if (hour in state.start..state.end) ringTextColor
                                else ringTextColor.copy(alpha = .5f)
                            } else {
                                if ((hour >= state.start && hour <= 23) || (hour <= state.end)) {
                                    ringTextColor
                                } else ringTextColor.copy(alpha = .5f)
                            }
                            val textStyle = TextStyle.Default.copy(
                                color = textColor,
                                fontSize = (size.minDimension / FONT_SCALE).sp
                            )
                            drawText(
                                textMeasurer = measurer,
                                text = text,
                                style = textStyle,
                                topLeft = Offset(
                                    textX.toFloat() - textSize.size.width / 2,
                                    textY.toFloat() - textSize.size.height / 2
                                )
                            )
                        }

                        val pointerX = (cos(radians) * ringSize) + size.center.x
                        val pointerY = (sin(radians) * ringSize) + size.center.y
                        val pointer = Offset(pointerX.toFloat(), pointerY.toFloat())
                        val copy = points.toMutableMap()
                        if (copy.values.find { it.x == pointer.x && it.y == pointer.y } == null) {
                            copy[hour] = pointer
                        }
                        points = copy

                        if (isHalf) {
                            drawCircle(
                                color = selectedArcColor.copy(.25f),
                                radius = ringWidth / 30f,
                                center = pointer
                            )
                        }

                        if (isHour) {
                            drawCircle(
                                color = selectedArcColor.copy(.25f),
                                radius = ringWidth / 20f,
                                center = pointer
                            )
                        }

                        if (hour == state.start || hour == state.end) {
                            if (abs(state.end - state.start) > 1) {
                                drawCircle(
                                    color = selectedArcColor.copy(.25f),
                                    radius = ringWidth * 1.2f / 2f,
                                    center = pointer
                                )
                            }
                            val factor = if (isHour) 2.5f else 3f
                            if (hour == state.start) {
                                drawCircle(
                                    color = startColor,
                                    radius = ringWidth / factor,
                                    center = pointer
                                )
                            }
                            if (hour == state.end) {
                                drawCircle(
                                    color = endColor,
                                    radius = ringWidth / factor,
                                    center = pointer
                                )
                            }
                        }

                        angle = angle + QUANT / 2
                    }
                }
            )
        }
    )
}