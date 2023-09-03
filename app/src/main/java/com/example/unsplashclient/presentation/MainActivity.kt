package com.example.unsplashclient.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.unsplashclient.presentation.photo_detail.PhotoDetailScreen
import com.example.unsplashclient.presentation.search_photos.SearchPhotosScreen
import com.example.unsplashclient.presentation.theme.UnsplashClientTheme
import dagger.hilt.android.AndroidEntryPoint

/**
 * 画面遷移のルートを管理するenumクラス
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnsplashClientTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // navControllerのインスタンスの生成
                    val navController = rememberNavController()
                    NavHost(
                        // 画面遷移の管理を行うnavControllerを設定
                        navController = navController,
                        // 画面遷移の開始画面を設定
                        startDestination = ScreenRoute.SearchPhotosScreen.route,
                    ){
                        // 画像検索画面
                        composable(route = ScreenRoute.SearchPhotosScreen.route){
                            SearchPhotosScreen(navController)
                        }
                        // 画像詳細表示画面
                        composable(route = ScreenRoute.PhotoDetailScreen.route + "/{photoId}") {
                            PhotoDetailScreen()
                        }
                    }
                }
            }
        }
    }
}