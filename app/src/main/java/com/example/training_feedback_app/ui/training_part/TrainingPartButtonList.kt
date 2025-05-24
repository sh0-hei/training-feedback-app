package com.example.training_feedback_app.ui.training_part

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.training_feedback_app.ui.component.TrainingPartButton

@Composable
fun ArmPartButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    TrainingPartButton(
        partName = "腕",
        onClick = onClick,
        modifier = modifier
    )
}

@Composable
fun ChestPartButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    TrainingPartButton(
        partName = "胸",
        onClick = onClick,
        modifier = modifier,
        enabled = false
    )
}

@Composable
fun BackPartButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    TrainingPartButton(
        partName = "背中",
        onClick = onClick,
        modifier = modifier,
        enabled = false
    )
}

@Composable
fun ShoulderPartButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    TrainingPartButton(
        partName = "肩",
        onClick = onClick,
        modifier = modifier,
        enabled = false
    )
}

@Composable
fun LegPartButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    TrainingPartButton(
        partName = "脚",
        onClick = onClick,
        modifier = modifier,
        enabled = false
    )
}