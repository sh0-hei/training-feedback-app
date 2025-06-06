package com.example.training_feedback_app.ui.screen

import android.os.Handler
import android.os.Looper
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.training_feedback_app.speech.SpeechManager
import com.example.training_feedback_app.ui.component.FinishWorkoutButton
import com.example.training_feedback_app.ui.component.StartWorkoutButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CaptureScreen(
    navController: NavController,
    speechManager: SpeechManager,
    partName: String,
    menuName: String
) {
    var isRecording by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("フォーム撮影中：$menuName") }
            )
        },
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 32.dp), // ナビゲーションバーとの重なりを防ぐ
                contentAlignment = Alignment.Center
            ) {
                if (isRecording) {
                    FinishWorkoutButton(onClick = { finishWorkout(navController) })
                } else {
                    StartWorkoutButton(onClick = {
                        startWorkout(speechManager)
                        isRecording = true
                    })
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            Text("カメラプレビューは未実装です")
        }
    }
}

// 開始処理（音声フィードバック＋カメラ起動＋10秒後のフィードバック）
private fun startWorkout(speechManager: SpeechManager) {
    // 音声フィードバックで開始を通知
    speechManager.speak("撮影を開始します。")

    // TODO: カメラ起動処理をここに記述する

    // 10秒後にフィードバックを読み上げる
    Handler(Looper.getMainLooper()).postDelayed({
        speechManager.speak("腕の位置が下がっています。しっかりと引き付けましょう。")
    }, 10_000)
}

// 終了処理（画面遷移）
private fun finishWorkout(navController: NavController) {
    navController.navigate("result")
}