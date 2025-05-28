package com.example.training_feedback_app.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ArmPartButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    TrainingPartButton(
        partName = "腕",
        onClick = onClick,
        modifier = modifier,
        enabled = enabled
    )
}

@Composable
fun ChestPartButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    TrainingPartButton(
        partName = "胸",
        onClick = onClick,
        modifier = modifier,
        enabled = enabled
    )
}

@Composable
fun BackPartButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    TrainingPartButton(
        partName = "背中",
        onClick = onClick,
        modifier = modifier,
        enabled = enabled
    )
}

@Composable
fun ShoulderPartButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    TrainingPartButton(
        partName = "肩",
        onClick = onClick,
        modifier = modifier,
        enabled = enabled
    )
}

@Composable
fun LegPartButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    TrainingPartButton(
        partName = "脚",
        onClick = onClick,
        modifier = modifier,
        enabled = enabled
    )
}