package com.example.unsplashclient.domain.use_case

import com.example.unsplashclient.common.NetworkResponse
import com.example.unsplashclient.data.remote.toPhotoDetail
import com.example.unsplashclient.domain.model.PhotoDetail
import com.example.unsplashclient.domain.repository.PhotoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * 写真詳細取得ユースケースクラス
 */
class GetPhotoDetailUseCase @Inject constructor(
    private val repository: PhotoRepository
){
    /**
     *  写真詳細取得処理
     */
    operator fun invoke(photoId: String) : Flow<NetworkResponse<PhotoDetail>> = flow {
        try {
            // 通信中のローディング状態をemit()
            emit(NetworkResponse.Loading<PhotoDetail>())
            // リポジトリから写真詳細を取得してphotoDetailモデルに変換
            val photoDetail =  repository.getPhotoId(photoId).toPhotoDetail()
            // 成功時はリストをemit()
            emit(NetworkResponse.Success<PhotoDetail>(photoDetail))
        } catch (e: Exception) {
            // 失敗時はエラーメッセージをemit()
            emit(NetworkResponse.Failure<PhotoDetail>(e.message.toString()))
        }
    }
}