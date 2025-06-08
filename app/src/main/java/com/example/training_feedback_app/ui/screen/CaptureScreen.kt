package com.example.training_feedback_app.ui.screen

import android.os.Handler
import android.os.Looper
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.training_feedback_app.R
import com.example.training_feedback_app.speech.SpeechManager
import com.example.training_feedback_app.ui.component.FinishWorkoutButton
import com.example.training_feedback_app.ui.component.StartWorkoutButton

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.unit.em

val numberedInstructions = listOf(
    "肩幅くらいに足を開いて立ち、両手にダンベルを持つ",
    "このとき、手のひらは前方に向ける",
    "肩をリラックスさせて背骨をまっすぐに伸ばす",
    "ダンベルを持ったまま、ひじを固定して持ち上げる動作をおこなう",
    "ひじが完全に曲がるまで持ち上げた後、ダンベルをゆっくりと元の位置に降ろす"
)

@Composable
fun CaptureScreen(
    navController: NavController,
    speechManager: SpeechManager,
    partName: String,
    menuName: String
) {
    var isRecording by remember { mutableStateOf(false) }

    Scaffold(
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
            // Text("カメラプレビューは未実装です")
            Image(
                painter = painterResource(id = R.drawable.dumbbell_curl_reference),
                contentDescription = "dumbbell_curl_reference.png",
                modifier = Modifier
                    .align(Alignment.Center)
                    .offset(y = (-130).dp)
                    .size(300.dp),
                alpha = 0.85f
            )
            // Text応急処置
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 300.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                numberedInstructions.forEachIndexed { index, text ->
                    Text(
                        buildAnnotatedString {
                            pushStyle(
                                ParagraphStyle(
                                    textIndent = TextIndent(firstLine = 0.em, restLine = 1.1.em)
                                )
                            )
                            append("${index + 1}. $text")
                            pop()
                        },
                        modifier = Modifier.padding(horizontal = 30.dp)
                    )
                }
            }
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