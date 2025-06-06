package com.example.training_feedback_app.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.training_feedback_app.speech.SpeechManager
import com.example.training_feedback_app.ui.screen.HomeScreen
import com.example.training_feedback_app.ui.screen.TrainingMenuScreen
import com.example.training_feedback_app.ui.screen.CaptureScreen

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        // ホーム画面（部位選択）
        composable("home") {
            HomeScreen(navController = navController)
        }

        // メニュー選択画面（部位名を受け取る）
        composable("training_menu/{partName}") { backStackEntry ->
            val partName = backStackEntry.arguments?.getString("partName") ?: ""
            TrainingMenuScreen(navController = navController, partName = partName)
        }

        // 動画撮影画面（部位名・メニュー名を受け取る）
        composable(
            route = "capture/{partName}/{menuName}",
            arguments = listOf(
                navArgument("partName") { type = NavType.StringType },
                navArgument("menuName") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val partName = backStackEntry.arguments?.getString("partName") ?: ""
            val menuName = backStackEntry.arguments?.getString("menuName") ?: ""
            val context = LocalContext.current
            val speechManager = remember { SpeechManager(context) }
            CaptureScreen(
                navController = navController,
                speechManager = speechManager,
                partName = partName,
                menuName = menuName
            )
        }

        // 結果画面（MVP開発段階では仮画面）
        composable("result") {
            // TODO: ResultScreen を実装後に追加
        }
    }
}