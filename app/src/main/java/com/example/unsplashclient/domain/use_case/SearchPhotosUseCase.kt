package com.example.unsplashclient.domain.use_case

import com.example.unsplashclient.common.NetworkResponse
import com.example.unsplashclient.data.remote.toPhotos
import com.example.unsplashclient.domain.model.Photo
import com.example.unsplashclient.domain.repository.PhotoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 *  検索ユースケースクラス
 */
class SearchPhotosUseCase @Inject constructor(
    private val repository: PhotoRepository
){
    /**
     *  検索処理
     */
     operator fun invoke(query: String): Flow<NetworkResponse<List<Photo>>> = flow {
        try {
            // 通信中のローディング状態をemit()
            emit(NetworkResponse.Loading<List<Photo>>())
            // リポジトリから検索結果を取得してphotoモデルのリストに変換
            val photos =  repository.searchPhotos(query).toPhotos()
            // 成功時はリストをemit()
            emit(NetworkResponse.Success<List<Photo>>(photos))
        } catch (e: Exception) {
            // 失敗時はエラーメッセージをemit()
            emit(NetworkResponse.Failure<List<Photo>>(e.message.toString()))
        }
    }
}