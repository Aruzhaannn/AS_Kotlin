package com.example.my_app2.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
                val animatedCounter by animateIntAsState(targetValue = counter)

                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(Color(0xFF2196F3), Color(0xFF90CAF9))
                            )
                        )
                ) { padding ->

                    Column(
                        modifier = Modifier
                            .padding(padding)
                            .fillMaxSize()
                            .padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {

                        Card(
                            shape = RoundedCornerShape(16.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.9f)),
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Column(
                                modifier = Modifier
                                    .padding(32.dp)
                                    .fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = "Hello $text",
                                    style = MaterialTheme.typography.headlineMedium
                                )

                                Spacer(modifier = Modifier.height(16.dp))

                                Text(
                                    text = "$animatedCounter",
                                    fontSize = 48.sp,
                                    color = Color(0xFF1976D2)
                                )

                                Spacer(modifier = Modifier.height(16.dp))

                                Button(
                                    onClick = { viewModel.onButtonClick() },
                                    shape = RoundedCornerShape(12.dp)
                                ) {
                                    Text("НАЖМИ МЕНЯ")
                                }

                                Spacer(modifier = Modifier.height(12.dp))

                                Button(
                                    onClick = { viewModel.incrementCounter() },
                                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF64B5F6)),
                                    shape = RoundedCornerShape(12.dp)
                                ) {
                                    Text("Увеличить счётчик", color = Color.White)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
