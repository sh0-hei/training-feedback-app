package com.example.training_feedback_app.data

// トレーニング部位のデータクラス
data class TrainingPart(
    val name: String,
    val enabled: Boolean
)

// 部位一覧の定義（表示順）
val trainingPartList = listOf(
    TrainingPart("腕", true),
    TrainingPart("肩", true),
    TrainingPart("胸", true),
    TrainingPart("腹", true),
    TrainingPart("背中", true),
    TrainingPart("下半身", true)
)