package com.example.training_feedback_app.data

// トレーニング部位のデータクラス
data class TrainingPart(
    val name: String,
    val enabled: Boolean
)

// 部位一覧の定義（表示順）
val trainingPartList = listOf(
    TrainingPart(name = "腕 - Arm -", enabled = true),
    TrainingPart(name = "肩 - Shoulder -", enabled = true),
    TrainingPart(name = "胸 - Brest -", enabled = true),
    TrainingPart(name = "腹 - Belly -", enabled = true),
    TrainingPart(name = "背中 - Back -", enabled = true),
    TrainingPart(name = "下半身 - Lower body -", enabled = true)
)