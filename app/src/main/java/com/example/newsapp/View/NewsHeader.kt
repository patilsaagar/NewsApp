package com.example.newsapp.View

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun NewsHeader() {
    Box(modifier = Modifier
        .fillMaxWidth()
        .background(Color(20, 47, 64)),
        contentAlignment = Alignment.Center
       ) {
        Text(text = "N E W S",
        style = TextStyle(
            fontSize = 40.sp,
            fontWeight = FontWeight.W900,
            color = Color.White
            )
        )
    }
}