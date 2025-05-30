package com.example.training_feedback_app.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.training_feedback_app.data.trainingMenuMap

// トレーニングメニューの選択セクション
// メニューリストをもとにボタンを縦に並べて表示する
@Composable
fun TrainingMenuListSection(
    partName: String,
    onMenuSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val menuList = trainingMenuMap[partName] ?: emptyList()

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        menuList.forEach { menu ->
            TrainingMenuButton(
                menuName = menu.name,
                onClick = { onMenuSelected(menu.name) }
            )
        }
    }
}