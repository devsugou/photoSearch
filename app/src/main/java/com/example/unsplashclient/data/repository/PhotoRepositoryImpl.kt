package com.example.unsplashclient.data.repository

import com.example.unsplashclient.data.remote.PhotoDetailDto
import com.example.unsplashclient.data.remote.SearchPhotosResultDto
import com.example.unsplashclient.data.remote.UnsplashApi
import com.example.unsplashclient.domain.repository.PhotoRepository
import javax.inject.Inject

/**
 * 画像検索リポジトリ実装クラス
 */
class PhotoRepositoryImpl @Inject constructor(
    private val unsplashApi: UnsplashApi
) : PhotoRepository {

    //キャッシュなど実装も想定されるが、今回はAPIのみの実装とする。

    /**
     * 検索ワード(query)に対する写真の結果を1ページ分(SearchPhotosResultDto)取得する。
     */
    override suspend fun searchPhotos(query: String) : SearchPhotosResultDto{
        return unsplashApi.searchPhotos(query)
    }

    /**
     * 指定したIDの写真の詳細情報を取得する。
     */
    override suspend fun getPhotoId(photoId: String) : PhotoDetailDto{
        return unsplashApi.getPhotoById(photoId)
    }

}