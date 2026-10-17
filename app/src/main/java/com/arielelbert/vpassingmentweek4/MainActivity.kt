package com.arielelbert.vpassingmentweek4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var currentScreen by remember { mutableStateOf("menu") }

            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                when (currentScreen) {
                    "menu" -> MainMenuScreen(
                        onSelectSoal1 = { currentScreen = "soal1" },
                        onSelectSoal2 = { currentScreen = "soal2" }
                    )
                    "soal1" -> Box(modifier = Modifier.fillMaxSize()) {
                        CarApp()
                        Button(
                            onClick = { currentScreen = "menu" },
                            modifier = Modifier
                                .padding(16.dp)
                                .align(Alignment.TopEnd),
                            colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray)
                        ) {
                            Text(text = "Menu", fontSize = 12.sp)
                        }
                    }
                    "soal2" -> Box(modifier = Modifier.fillMaxSize()) {
                        PartyRosterApp()
                        Button(
                            onClick = { currentScreen = "menu" },
                            modifier = Modifier
                                .padding(16.dp)
                                .align(Alignment.TopEnd),
                            colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray)
                        ) {
                            Text(text = "Menu", fontSize = 12.sp, color = Color.White)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MainMenuScreen(
    onSelectSoal1: () -> Unit,
    onSelectSoal2: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Tugas Week 4",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = "Pilih Soal",
            fontSize = 14.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(40.dp))
        Button(
            onClick = onSelectSoal1,
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp)
        ) {
            Text(text = "Soal 1", fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = onSelectSoal2,
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1A1C29))
        ) {
            Text(text = "Soal 2", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.Yellow)
        }
    }
}