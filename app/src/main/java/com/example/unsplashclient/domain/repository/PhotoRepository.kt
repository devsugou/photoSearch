package com.example.unsplashclient.domain.repository

import com.example.unsplashclient.data.remote.PhotoDetailDto
import com.example.unsplashclient.data.remote.SearchPhotosResultDto

/**
 * 画像検索リポジトリインターフェース
 */
interface PhotoRepository {
    /**
     * 検索ワード(query)に対する写真の結果を1ページ分(SearchPhotosResultDto)取得する。
     */
    suspend fun searchPhotos(query: String):SearchPhotosResultDto

    /**
     * 指定したIDの写真の詳細情報を取得する。
     */
    suspend fun getPhotoId(photoId: String): PhotoDetailDto
}