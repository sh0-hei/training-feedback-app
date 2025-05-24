package com.example.training_feedback_app.ui.training_menu

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.training_feedback_app.ui.component.TrainingMenuButton

@Composable
fun DumbbellArmCurlButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    TrainingMenuButton(
        menuName = "ダンベルアームカール",
        onClick = onClick,
        modifier = modifier
    )
}

@Composable
fun SquatButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    TrainingMenuButton(
        menuName = "スクワット",
        onClick = onClick,
        modifier = modifier,
        enabled = false
    )
}

@Composable
fun PushUpButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    TrainingMenuButton(
        menuName = "腕立て伏せ",
        onClick = onClick,
        modifier = modifier,
        enabled = false
    )
}

@Composable
fun ShoulderPressButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    TrainingMenuButton(
        menuName = "ショルダープレス",
        onClick = onClick,
        modifier = modifier,
        enabled = false
    )
}

@Composable
fun LateralRaiseButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    TrainingMenuButton(
        menuName = "サイドレイズ",
        onClick = onClick,
        modifier = modifier,
        enabled = false
    )
}

@Composable
fun DeadLiftButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    TrainingMenuButton(
        menuName = "デッドリフト",
        onClick = onClick,
        modifier = modifier,
        enabled = false
    )
}

@Composable
fun SitUpButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    TrainingMenuButton(
        menuName = "シットアップ",
        onClick = onClick,
        modifier = modifier,
        enabled = false
    )
}

@Composable
fun BenchPressButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    TrainingMenuButton(
        menuName = "ベンチプレス",
        onClick = onClick,
        modifier = modifier,
        enabled = false
    )
}