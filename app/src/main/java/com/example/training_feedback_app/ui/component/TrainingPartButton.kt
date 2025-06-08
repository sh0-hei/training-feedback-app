package com.example.training_feedback_app.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.training_feedback_app.ui.style.hoverableShadow
import com.example.training_feedback_app.ui.theme.DefaultButtonShape
import com.example.training_feedback_app.ui.theme.GradientStart
import com.example.training_feedback_app.ui.theme.GradientEnd

@Composable
fun TrainingPartButton(
    partName: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp)
            .height(60.dp)
            .hoverableShadow(
                onClick = onClick,
                enabled = enabled,
                shape = DefaultButtonShape
            )
            .clip(DefaultButtonShape)
            .background(
                brush = Brush.verticalGradient(listOf(GradientStart, GradientEnd)),
                shape = DefaultButtonShape
            )
            // .clickable(enabled = enabled) { onClick() }
    ) {
        Text(
            text = partName,
            color = Color.White,
            fontSize = 20.sp
        )
    }
}