package com.example.unsplashclient.presentation.search_photos

import com.example.unsplashclient.domain.model.Photo

/**
 * 画像検索画面の状態を管理するデータクラス
 */
data class SearchPhotosState(
    // 画面のロード中かどうかのステータス
    val isLoading: Boolean = false,
    // 検索結果の画像リスト
    val photos: List<Photo> = emptyList(),
    // 検索結果のエラーメッセージ
    val error: String? = null
)
