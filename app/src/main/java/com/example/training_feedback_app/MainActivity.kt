package com.example.training_feedback_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.remember
import androidx.navigation.compose.rememberNavController
import com.example.training_feedback_app.speech.SpeechManager
import com.example.training_feedback_app.ui.navigation.AppNavGraph
import com.example.training_feedback_app.ui.theme.TrainingFeedbackAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TrainingFeedbackAppTheme(
                dynamicColor = false
            ) {
                val navController = rememberNavController()
                val speechManager = remember { SpeechManager(this) }

                AppNavGraph(
                    navController = navController,
                    speechManager = speechManager
                )
            }
        }
    }
}