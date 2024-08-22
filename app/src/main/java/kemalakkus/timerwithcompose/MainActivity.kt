package kemalakkus.timerwithcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var isTimerRunning by remember { mutableStateOf(false) }
            var isRestarting by remember { mutableStateOf(false) }
            val backgroundColor by animateColorAsState(
                targetValue = when {
                    isRestarting -> Color(0xFF270D4A)
                    isTimerRunning -> Color(0xFF270D4A)
                    else -> Color(0xFF29172D)
                },
                animationSpec = tween(durationMillis = 1000)
            )

            Surface(
                color = backgroundColor,
                modifier = Modifier.fillMaxSize()
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                ) {
                    Timer(
                        totalTime = 10L * 1000L,
                        handleColor = Color(0xFF962d70),
                        inactiveBarColor = Color.DarkGray,
                        activeBarColor = Color(0xFF962d70),
                        onTimerStateChange = { isRunning ->
                            isTimerRunning = isRunning
                        },
                        onRestartingChange = { isRestartingState ->
                            isRestarting = isRestartingState
                        }
                    )
                }
            }
        }
    }
}