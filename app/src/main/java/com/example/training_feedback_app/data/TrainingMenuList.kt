package com.example.training_feedback_app.data

// トレーニングメニューのデータクラス
data class TrainingMenu(val name: String)

// 部位ごとのメニュー一覧の定義（表示順）
val trainingMenuMap = mapOf(
    "腕" to listOf(
        TrainingMenu("ダンベルアームカール"),
        TrainingMenu("バーベルアームカール"),
        TrainingMenu("コンセントレーションカール"),
        TrainingMenu("ライイングトライセプスエクステンション"),
        TrainingMenu("トライセプスキックバック")
    ),
    "肩" to listOf(
        TrainingMenu("ショルダープレス"),
        TrainingMenu("サイドレイズ"),
        TrainingMenu("シュラッグ"),
        TrainingMenu("バーベルアップライトロウ"),
        TrainingMenu("ベントオーバーラテラルレイズ")
    ),
    "胸" to listOf(
        TrainingMenu("ダンベルベンチプレス"),
        TrainingMenu("ダンベルフライ"),
        TrainingMenu("プルオーバー"),
        TrainingMenu("プッシュアップ"),
        TrainingMenu("チェストプレス")
    ),
    "腹" to listOf(
        TrainingMenu("サイドベント"),
        TrainingMenu("シットアップ"),
        TrainingMenu("ニートゥチェスト")
    ),
    "背中" to listOf(
        TrainingMenu("ワンハンドロウ"),
        TrainingMenu("ベントオーバーロウ")
    ),
    "下半身" to listOf(
        TrainingMenu("スクワット"),
        TrainingMenu("フロントランジ"),
        TrainingMenu("スティフドレッグデッドリフト"),
        TrainingMenu("ヒップスラスト")
    )
)