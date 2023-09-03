package com.example.unsplashclient.presentation.search_photos

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.unsplashclient.common.NetworkResponse
import com.example.unsplashclient.domain.use_case.SearchPhotosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

/**
 * 画像検索画面の状態を管理するデータクラス
 */
@HiltViewModel
class SearchPhotosViewModel @Inject constructor(
    private val searchPhotosUseCase: SearchPhotosUseCase,
) : ViewModel(){
    // 画像検索画面の状態を管理する
    private val _state = mutableStateOf(SearchPhotosState())
    // 画像検索画面の状態を公開する
    val state: State<SearchPhotosState> = _state

    var query by mutableStateOf("programing")

    // 初期化処理
    init {
        searchPhotos()
    }

    /**
     * 画像検索を行う
     */
    fun searchPhotos(){
        searchPhotosUseCase(query).onEach { result ->
            when (result) {
                // 成功時
                is NetworkResponse.Success -> {
                    _state.value = SearchPhotosState(
                        isLoading = false,
                        photos = result.data ?: emptyList()
                    )
                }
                // 失敗時
                is NetworkResponse.Failure -> {
                    _state.value = SearchPhotosState(error = result.error)
                }
                // 通信中のローディング状態
                is NetworkResponse.Loading -> {
                    _state.value = SearchPhotosState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)// viewModelScopeでライフサイクルを管理
    }
}