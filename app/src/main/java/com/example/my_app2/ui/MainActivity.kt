package com.example.my_app2.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.my_app2.ui.theme.My_app2Theme
import com.example.my_app2.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            My_app2Theme {

                val text by viewModel.text.collectAsState()
                val counter by viewModel.counter.collectAsState()

                /* ---------- АНИМАЦИЯ ФОНА ---------- */
                val infiniteTransition = rememberInfiniteTransition(label = "bg")

                val animationProgress by infiniteTransition.animateFloat(
                    initialValue = 0f,
                    targetValue = 1f,
                    animationSpec = infiniteRepeatable(
                        animation = tween(4000, easing = LinearEasing),
                        repeatMode = RepeatMode.Reverse
                    ),
                    label = "progress"
                )

                val topColor =
                    if (animationProgress < 0.5f) Color(0xFF42A5F5) else Color(0xFF7E57C2)
                val bottomColor =
                    if (animationProgress < 0.5f) Color(0xFF90CAF9) else Color(0xFFCE93D8)

                /* ---------- АНИМАЦИЯ СЧЁТЧИКА ---------- */
                val scale by animateFloatAsState(
                    targetValue = if (counter > 0) 1.3f else 1f,
                    animationSpec = tween(300),
                    label = "scale"
                )

                val counterColor by animateColorAsState(
                    targetValue = if (counter % 2 == 0)
                        Color(0xFF1E88E5)
                    else
                        Color(0xFFD81B60),
                    label = "counterColor"
                )

                Scaffold { padding ->

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                Brush.verticalGradient(
                                    colors = listOf(topColor, bottomColor)
                                )
                            )
                            .padding(padding)
                    ) {

                        Card(
                            modifier = Modifier
                                .align(Alignment.Center)
                                .padding(24.dp),
                            shape = RoundedCornerShape(24.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = Color.White.copy(alpha = 0.88f)
                            ),
                            elevation = CardDefaults.cardElevation(12.dp)
                        ) {

                            Column(
                                modifier = Modifier
                                    .padding(32.dp)
                                    .widthIn(min = 280.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {

                                Text(
                                    text = "Hello $text",
                                    style = MaterialTheme.typography.headlineMedium
                                )

                                Spacer(modifier = Modifier.height(20.dp))

                                Text(
                                    text = "$counter",
                                    fontSize = 56.sp,
                                    color = counterColor,
                                    modifier = Modifier.scale(scale)
                                )

                                Spacer(modifier = Modifier.height(24.dp))

                                Button(
                                    onClick = { viewModel.onButtonClick() },
                                    shape = RoundedCornerShape(16.dp),
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Text("Сменить текст")
                                }

                                Spacer(modifier = Modifier.height(12.dp))

                                Button(
                                    onClick = { viewModel.incrementCounter() },
                                    shape = RoundedCornerShape(16.dp),
                                    modifier = Modifier.fillMaxWidth(),
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Color(0xFF1E88E5)
                                    )
                                ) {
                                    Text(
                                        "Увеличить счётчик",
                                        color = Color.White
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
