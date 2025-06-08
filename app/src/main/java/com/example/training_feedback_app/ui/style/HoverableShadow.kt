package com.example.training_feedback_app.ui.style

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.unit.dp
import com.example.training_feedback_app.ui.theme.DefaultButtonShape

@Composable
fun Modifier.hoverableShadow(
    onClick: () -> Unit,
    enabled: Boolean = true,
    shape: RoundedCornerShape = DefaultButtonShape
): Modifier {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed = interactionSource.collectIsPressedAsState().value

    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.95f else 1.0f,
        label = "buttonScale"
    )
    val elevation by animateFloatAsState(
        targetValue = if (isPressed) 1f else 16f,
        label = "buttonElevation"
    )

    return this
        .scale(scale)
        .shadow(
            elevation = elevation.dp,
            shape = shape,
            ambientColor = Color.Black.copy(alpha = 0.4f),
            spotColor = Color.Black.copy(alpha = 0.4f)
        )
        .clickable(
            interactionSource = interactionSource,
            indication = null,
            enabled = enabled,
            onClick = onClick
        )
}