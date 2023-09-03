package com.example.unsplashclient.data.remote


import com.example.unsplashclient.domain.model.Photo
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SearchPhotosResultDto(
    val results: List<Result>?,
    val total: Int?,
    @Json(name = "total_pages")
    val totalPages: Int?
)

/**
 * SearchPhotosResultDtoをPhoto(Model)に変換する
 */
fun SearchPhotosResultDto.toPhotos(): List<Photo> {
    return this.results!!.map {
        Photo(
            // 画像ID
            photoId = it.id!!,
            // 画像の説明
            description = it.description,
            // 画像へのlikeの数
            likes = it.likes,
            // 画像のリンク
            imageUrls = it.urls!!.raw!!,
            // 撮影者のユーザー名
            photographer = it.user?.username,
        )
    } ?: emptyList()
}