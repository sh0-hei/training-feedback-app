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

import android.content.Context
import android.content.Intent
import androidx.compose.ui.platform.LocalContext
import com.example.training_feedback_app.TrainerActivity

import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.unit.em

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CaptureScreen(
    navController: NavController,
    speechManager: SpeechManager,
    partName: String,
    menuName: String
) {
    var isRecording by remember { mutableStateOf(false) }

    val context = LocalContext.current

    Scaffold(
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 32.dp),
                contentAlignment = Alignment.Center
            ) {
                if (isRecording) {
                    FinishWorkoutButton(onClick = { finishWorkout(navController) })
                } else {
                    StartWorkoutButton(onClick = {
                        startWorkout(speechManager, context, partName, menuName)
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
            Image(
                painter = painterResource(id = R.drawable.dumbbell_curl_reference),
                contentDescription = "dumbbell_curl_reference.png",
                modifier = Modifier
                    .align(Alignment.Center)
                    .offset(y = (-130).dp)
                    .size(300.dp),
                alpha = 0.85f
            )

            val numberedInstructions = listOf(
                "肩幅くらいに足を開いて立ち、両手にダンベルを持つ",
                "このとき、手のひらは前方に向ける",
                "肩をリラックスさせて背骨をまっすぐに伸ばす",
                "ダンベルを持ったまま、ひじを固定して持ち上げる動作をおこなう",
                "ひじが完全に曲がるまで持ち上げた後、ダンベルをゆっくりと元の位置に降ろす"
            )

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

private fun startWorkout(
    speechManager: SpeechManager,
    context: Context,
    partName: String,
    menuName: String
) {
    // 音声フィードバックで開始を通知
    speechManager.speak("撮影を開始します。")

    // カメラ起動処理
    val intent = Intent(context, TrainerActivity::class.java).apply {
        putExtra("PART_NAME", partName)
        putExtra("MENU_NAME", menuName)
    }
    context.startActivity(intent)

    // 15秒後にフィードバックを読み上げる
    Handler(Looper.getMainLooper()).postDelayed({
        speechManager.speak("腕の位置が下がっています。しっかりと引き付けましょう。")
    }, 15_000)
}

private fun finishWorkout(navController: NavController) {
    navController.navigate("result")
}