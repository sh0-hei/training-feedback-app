# Training Feedback App 💪

リアルタイムで筋トレフォームを解析し、音声フィードバックを提供するAndroidアプリ。
スマホのカメラで撮影したトレーニング動画をAIが解析し、フォームの改善点を音声で指摘します。

---

## 機能一覧

- カメラ撮影によるフォーム取得
- 姿勢推定AIによるリアルタイム解析
- 音声フィードバック
- アカウント登録・削除（予定）
- トレーニングごとの履歴保存（予定）
- フォームの種類別グループ分け（予定）

---

## 使用技術

- Kotlin
- Android Studio
- Jetpack / Jetpack Compose（UI構築）
- MediaPipe / TensorFlow Lite（AI解析）
- TextToSpeech（音声フィードバック）

---

## セットアップ手順

```bash
git clone https://github.com/sh0-hei/training-feedback-app.git
cd training-feedback-app
# Android Studio で開いてビルド