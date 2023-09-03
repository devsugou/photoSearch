package com.example.unsplashclient.presentation

/**
 * 画面管理用のシールドクラス
 */
sealed class ScreenRoute(val route: String){
    object SearchPhotosScreen: ScreenRoute("search_photos_screen")
    object PhotoDetailScreen: ScreenRoute("photo_detail_screen")
}