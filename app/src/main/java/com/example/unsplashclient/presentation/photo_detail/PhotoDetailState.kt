package com.example.unsplashclient.presentation.photo_detail

import com.example.unsplashclient.domain.model.PhotoDetail

/**
 * 画像詳細画面の状態を管理するデータクラス
 */
class PhotoDetailState (
    // 画面のロード中かどうかのステータス
    val isLoading: Boolean = false,
    // 検索結果の画像リスト
    val photoDetail: PhotoDetail? = null,
    // 検索結果のエラーメッセージ
    val error: String? = null
)
