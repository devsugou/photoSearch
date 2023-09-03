package com.example.unsplashclient.data.remote

import com.example.unsplashclient.common.Constants
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * 画像検索通信インターフェース
 */
interface UnsplashApi {

    /**
     * 検索ワード(query)に対する写真の結果を1ページ分(SearchPhotosResultDto)取得する。
     */
    @Headers("Authorization: Client-ID ${Constants.API_KEY}")
    @GET("/search/photos")
    suspend fun searchPhotos(@Query("query") query:String):SearchPhotosResultDto

    /**
     * 指定したIDの写真の詳細情報を取得する。
     */
    @Headers("Authorization: Client-ID ${Constants.API_KEY}")
    @GET("/photos/{id}")
    suspend fun getPhotoById(@Path("id") photoId: String):PhotoDetailDto
}