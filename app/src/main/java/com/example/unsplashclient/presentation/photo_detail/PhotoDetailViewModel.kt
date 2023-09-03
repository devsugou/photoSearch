package com.example.unsplashclient.presentation.photo_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.unsplashclient.common.NetworkResponse
import com.example.unsplashclient.domain.use_case.GetPhotoDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

/**
 * 画像詳細画面のViewModel
 */
@HiltViewModel
class PhotoDetailViewModel @Inject constructor(
    // 画像詳細取得ユースケース
    private val getPhotoDetailUseCase: GetPhotoDetailUseCase,
    // 画面の状態を保持する
    saveStateHandle: SavedStateHandle
): ViewModel(){
        // 画像詳細画面の状態を管理する
        private val _state = mutableStateOf(PhotoDetailState())
        val state: State<PhotoDetailState> = _state

        init {
            // 画像詳細画面の状態を取得する
            saveStateHandle.get<String>("photoId")?.let { photoId ->
                getPhotoDetail(photoId)
            }
        }
        /**
         * 画像詳細を取得する
         */
        private fun getPhotoDetail(photoId: String){
            getPhotoDetailUseCase(photoId).onEach { result ->
                when (result) {
                    // 成功時
                    is NetworkResponse.Success -> {
                        _state.value = PhotoDetailState(photoDetail = result.data)
                    }
                    // 失敗時
                    is NetworkResponse.Failure -> {
                        _state.value = PhotoDetailState(error = result.error)
                    }
                    // 通信中のローディング状態
                    is NetworkResponse.Loading -> {
                        _state.value = PhotoDetailState(isLoading = true)
                    }
                }
            }.launchIn(viewModelScope)// viewModelScopeでライフサイクルを管理
        }
    }