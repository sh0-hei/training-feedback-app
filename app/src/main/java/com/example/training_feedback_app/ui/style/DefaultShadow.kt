package com.example.training_feedback_app.ui.style

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.draw.shadow
import com.example.training_feedback_app.ui.theme.DefaultButtonShape

fun Modifier.defaultShadow(): Modifier = this.shadow(
    elevation = 16.dp,
    shape = DefaultButtonShape,
    ambientColor = Color.Black.copy(alpha = 0.8f),
    spotColor = Color.Black.copy(alpha = 0.8f)
)