package com.example.unsplashclient.presentation.search_photos

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.unsplashclient.presentation.ScreenRoute
import com.example.unsplashclient.presentation.search_photos.components.PhotoThumbnail
import com.example.unsplashclient.presentation.search_photos.components.SearchBar

/**
 * 画像検索画面
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchPhotosScreen(
    navController: NavController,
    viewModel: SearchPhotosViewModel = hiltViewModel(),
) {
    val state = viewModel.state.value

    Scaffold(
        topBar = {
            SearchBar(searchText = viewModel.query,
                onSearchTextChange = { viewModel.query = it },
                onDone = { viewModel.searchPhotos() },
            )
        }
    ) { paddingValues ->
        Box(modifier = Modifier.fillMaxSize()) {
            when{
                state.isLoading -> {
                    //ローディング表示
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
                !state.error.isNullOrBlank() -> {
                    //エラー表示
                    Text(
                        text = state.error,
                        modifier = Modifier.align(Alignment.Center),
                        color = MaterialTheme.colorScheme.error,
                    )
                }
                else -> {
                    //画像一覧表示
                    LazyColumn(modifier = Modifier.padding(paddingValues)){
                        items(state.photos) { photo ->
                            PhotoThumbnail(
                                photo = photo,
                                //画像をタップした際に画像詳細画面に遷移する
                                onClick = {
                                    navController.navigate(ScreenRoute.PhotoDetailScreen.route + "/${photo.photoId}")
                                },
                            )
                        }
                    }
                }
            }

        }
    }
}