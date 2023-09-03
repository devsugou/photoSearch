package com.example.unsplashclient.common

/**
 *  ネットワークレスポンスクラス
 */
sealed class NetworkResponse<T>(
    val data: T? = null,
    val error: String? = null
) {
    //成功時
    class Success<T>(data: T) : NetworkResponse<T>(data = data)
    //エラー時
    class Failure<T>(error: String) : NetworkResponse<T>(error = error)
    //ローディング時
    class Loading<T> : NetworkResponse<T>()
}