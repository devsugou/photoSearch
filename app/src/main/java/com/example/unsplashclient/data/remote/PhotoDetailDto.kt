package com.example.unsplashclient.data.remote


import com.example.unsplashclient.domain.model.PhotoDetail
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PhotoDetailDto(
    @Json(name = "blur_hash")
    val blurHash: String?,
    val color: String?,
    @Json(name = "created_at")
    val createdAt: String?,
    @Json(name = "current_user_collections")
    val currentUserCollections: List<CurrentUserCollection>?,
    val description: String?,
    val downloads: Int?,
    val exif: Exif?,
    val height: Int?,
    val id: String?,
    @Json(name = "liked_by_user")
    val likedByUser: Boolean?,
    val likes: Int?,
    val links: LinksXX?,
    val location: Location?,
    @Json(name = "public_domain")
    val publicDomain: Boolean?,
    val tags: List<Tag>?,
    @Json(name = "updated_at")
    val updatedAt: String?,
    val urls: UrlsX?,
    val user: UserX?,
    val width: Int?
)

/**
 * PhotoDetailDtoをPhotoDetail(Model)に変換する
 */
fun PhotoDetailDto.toPhotoDetail(): PhotoDetail {
    return PhotoDetail(
        // 画像の説明
        description = description,
        // 画像へのlikeの数
        likes = likes,
        // 画像のリンク
        imageUrls = urls!!.raw!!,
        // 撮影者のユーザー名
        photographer = user?.username,
        // 撮影したカメラの名前
        camera = exif?.name,
        // 撮影した場所
        location = "${location?.city}, ${location?.country}",
        // Unsplashでダウンロードされた回数
        downloads = downloads,
    )
}