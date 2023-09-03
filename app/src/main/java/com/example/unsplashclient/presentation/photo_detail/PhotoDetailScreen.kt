package com.example.unsplashclient.presentation.photo_detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.unsplashclient.domain.model.PhotoDetail
import com.example.unsplashclient.presentation.components.CountLable

/**
 * 画像詳細画面
 */
@Composable
fun PhotoDetailScreen(
    viewModel: PhotoDetailViewModel = hiltViewModel(),

) {
    // 画像詳細画面の状態を取得する
    val state = viewModel.state.value

    Box(modifier = Modifier.fillMaxSize()) {
        when {
            state.isLoading -> {
                // ローディング中の表示
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
            !state.error.isNullOrBlank() -> {
                //エラー表示
                Text(
                    text = state.error,
                    modifier = Modifier.align(Alignment.Center),
                    color = MaterialTheme.colorScheme.error,
                )
            }
            else -> {
                // 画像詳細表示
                state.photoDetail ?.let { photoDetail ->
                    // 画像詳細表示
                    PhotoDetailContent(photoDetail = photoDetail)
                }
            }
        }
    }
}

/**
 * 画像詳細画面のコンテンツ
 */
@Composable
fun PhotoDetailContent(photoDetail: PhotoDetail) {
    // Columnで縦表示
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        Box(modifier = Modifier.heightIn(min = 200.dp)) {
            var isLoadingImage by remember { mutableStateOf(true) }
            if (isLoadingImage) {
                // 画像の読み込み中はプログレスバーを表示
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            // 画像を表示
            AsyncImage(
                // 画像のURL
                model = photoDetail.imageUrls,
                // 画像の説明
                contentDescription = photoDetail.description,
                // 画像のサイズを画面幅に合わせる
                modifier = Modifier
                    .fillMaxWidth()
                    // 画像の角を丸くする
                    .clip(
                        RoundedCornerShape(
                            topStartPercent = 0,
                            topEndPercent = 0,
                            bottomEndPercent = 5,
                            bottomStartPercent = 5,
                        )
                    ),
                onSuccess = {
                    // 画像の読み込みが完了したらプログレスバーを非表示
                    isLoadingImage = false
                },
            )
        }
        Column(modifier = Modifier.padding(10.dp)){
            // Descriptionを表示
            Text(
                text = photoDetail.description ?: "No description",
                style = MaterialTheme.typography.headlineLarge,
            )
            Spacer(modifier = Modifier.height(20.dp))
            // Photographerを表示
            Text(
                text = photoDetail.photographer ?: "Unknown photographer",
                style = MaterialTheme.typography.bodySmall,
            )
            Spacer(modifier = Modifier.height(20.dp))
            // いいねの数を表示
            CountLable(
                imageVector = Icons.Default.Favorite,
                count = photoDetail.likes ?: 0,
                iconTint = Color.Magenta,
            )

            // Downloadの表示
            CountLable(
                imageVector = Icons.Default.Share,
                count = photoDetail.downloads ?: 0,
                iconTint = Color.Green,
            )
            // Cameraの表示
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Camera: ${photoDetail.camera}"
            )
            // Locationの表示
            Text(
                text = "Location: ${photoDetail.location}"
            )
        }
    }
}