package com.example.training_feedback_app.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.training_feedback_app.data.trainingPartList

// トレーニング部位の選択セクション
// 部位リストをもとにボタンを縦に並べて表示する
@Composable
fun TrainingPartListSection(
    onPartSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        trainingPartList.forEach { part ->
            TrainingPartButton(
                partName = part.name,
                onClick = { onPartSelected(part.name) },
                enabled = part.enabled
            )
        }
    }
}