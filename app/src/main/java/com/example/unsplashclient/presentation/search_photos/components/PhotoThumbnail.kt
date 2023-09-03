package com.example.unsplashclient.presentation.search_photos.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.unsplashclient.domain.model.Photo
import com.example.unsplashclient.presentation.components.CountLable
import com.example.unsplashclient.presentation.theme.UnsplashClientTheme

/**
 * 画像サムネイルクラス
 */
@Composable
fun PhotoThumbnail(
    photo: Photo,
    // クリックされた際の挙動
    onClick: (Photo) -> Unit
) {
    Box(
        modifier = Modifier
            .background(Color.Black)
            .height(200.dp)
            .clickable { onClick(photo) },
        contentAlignment = Alignment.BottomCenter,
        ) {
        // 画像の読み込み中はプログレスバーを表示
        CircularProgressIndicator(
            modifier = Modifier.align(Alignment.Center)
        )
        // 背景画像を表示
        AsyncImage(
            model = photo.imageUrls,
            contentDescription = photo.description,
            modifier = Modifier.fillMaxWidth(),
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Black.copy(alpha = 0.5f))
                .padding(10.dp)

        ) {
            Column(
                modifier = Modifier.fillMaxWidth(0.8f),
            ) {
                // 画像の説明を表示
                Text(
                    text = photo.description ?: "No description",
                    color = Color.White,
                    // MaterialThemeを使用してテキストのスタイルを設定 TODO:何故かH6が設定できない
                        style = MaterialTheme.typography.headlineLarge,
                )
                // 撮影者名を表示
                Text(
                    text = photo.photographer ?: "Unknown photographer",
                    color = Color.White,
                    // MaterialThemeを使用してテキストのスタイルを設定 TODO:何故かbody2が設定できない
                    style = MaterialTheme.typography.bodySmall
                )
            }
            Spacer(modifier = Modifier.weight(1f))

            // いいねの数を表示
            CountLable(
                imageVector = Icons.Default.Favorite,
                count = photo.likes ?: 0,
                iconTint = Color.Magenta,
                color = Color.White,
            )
        }
    }
}

/**
 * プレビュー用のコンポーネント
 */
@Preview
@Composable
private fun PhotoThumbnailPreview() {
    val photo = Photo(
        photoId = "",
        description = "Image description",
        likes = 100,
        imageUrls = "imageUrls",
        photographer = "photographer",
    )

    UnsplashClientTheme {
        PhotoThumbnail(
            photo = photo,
            onClick = {}
        )
    }
}