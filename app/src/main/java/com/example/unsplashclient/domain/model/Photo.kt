package com.example.unsplashclient.domain.model

/**
 * 画像検索結果のモデルクラス
 */
data class Photo(
    // 画像ID
    val photoId: String,
    // 画像の説明
    val description: String?,
    // 画像へのlikeの数
    val likes: Int?,
    // 画像のリンク
    val imageUrls: String,
    // 撮影者のユーザー名
    val photographer: String?,
)
