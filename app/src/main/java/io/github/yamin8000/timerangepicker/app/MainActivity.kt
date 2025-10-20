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
import androidx.compose.ui.tooling.preview.PreviewLightDark
import io.github.yamin8000.timerangepicker.TimeRangePicker
import io.github.yamin8000.timerangepicker.app.ui.theme.AppTheme
import io.github.yamin8000.timerangepicker.rememberTimeRangePickerState
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
                                state = state
                            )
                        }
                    )
                }
            )
        }
    )
}