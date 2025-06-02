package com.example.training_feedback_app.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.training_feedback_app.ui.component.TrainingMenuListSection

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrainingMenuScreen(
    partName: String,
    navController: NavController
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("トレーニングメニュー（$partName）を選択") }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            TrainingMenuListSection(
                partName = partName,
                onMenuSelected = { selectedMenu: String ->
                    navController.navigate("capture/$partName/$selectedMenu")
                }
            )
        }
    }
}