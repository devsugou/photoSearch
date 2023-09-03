package com.example.unsplashclient.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

/**
 * いいねの数を表示するラベル
 */
@Composable
fun CountLable(
    imageVector: ImageVector,
    count: Int,
    iconTint: Color,
    modifier: Modifier = Modifier,
    color: Color = LocalContentColor.current.copy(alpha = 1f),
) {
    //Rowで水平表示
    Row (modifier = modifier){
        // いいねのアイコンを表示
        Icon(
            imageVector = imageVector,
            contentDescription = "likes",
            tint = iconTint,
        )
        Spacer(modifier = Modifier.width(5.dp))
        // 画像へのlikeの数を表示
        Text(
            text = count.toString(),
            color = color,
            style = MaterialTheme.typography.bodySmall,
        )
    }
}