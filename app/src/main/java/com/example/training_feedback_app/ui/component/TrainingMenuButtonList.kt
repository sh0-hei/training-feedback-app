package com.example.training_feedback_app.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

// 各トレーニングメニュー用ボタンの定義
// 各ボタンのロジック（表示名、クリック時の処理、有効／無効状態）を個別に設定
// ※スタイルは TrainingMenuButton.kt にて共通定義

@Composable
fun DumbbellArmCurlButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    TrainingMenuButton(
        menuName = "ダンベルアームカール",
        onClick = onClick,
        modifier = modifier,
        enabled = enabled
    )
}

@Composable
fun SquatButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    TrainingMenuButton(
        menuName = "スクワット",
        onClick = onClick,
        modifier = modifier,
        enabled = enabled
    )
}

@Composable
fun PushUpButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    TrainingMenuButton(
        menuName = "腕立て伏せ",
        onClick = onClick,
        modifier = modifier,
        enabled = enabled
    )
}

@Composable
fun ShoulderPressButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    TrainingMenuButton(
        menuName = "ショルダープレス",
        onClick = onClick,
        modifier = modifier,
        enabled = enabled
    )
}

@Composable
fun LateralRaiseButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    TrainingMenuButton(
        menuName = "サイドレイズ",
        onClick = onClick,
        modifier = modifier,
        enabled = enabled
    )
}

@Composable
fun DeadLiftButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    TrainingMenuButton(
        menuName = "デッドリフト",
        onClick = onClick,
        modifier = modifier,
        enabled = enabled
    )
}

@Composable
fun SitUpButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    TrainingMenuButton(
        menuName = "シットアップ",
        onClick = onClick,
        modifier = modifier,
        enabled = enabled
    )
}

@Composable
fun BenchPressButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    TrainingMenuButton(
        menuName = "ベンチプレス",
        onClick = onClick,
        modifier = modifier,
        enabled = enabled
    )
}