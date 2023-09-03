package com.example.unsplashclient.domain.model

/**
 * 画像詳細のモデルクラス
 */
data class PhotoDetail(
    // 画像の説明
    val description: String?,
    // 画像へのlikeの数
    val likes: Int?,
    // 画像のリンク
    val imageUrls: String,
    // 撮影者のユーザー名
    val photographer: String?,
    // 撮影したカメラの名前
    val camera: String?,
    // 撮影した場所
    val location: String?,
    // Unsplashでダウンロードされた回数
    val downloads: Int?,
)
