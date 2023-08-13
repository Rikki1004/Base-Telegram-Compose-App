package com.rikkimikki.telegramgallery.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.rikkimikki.telegramgallery.navigation.Screen
import com.rikkimikki.telegramgallery.domain.entity.FeedItem

@Composable
fun AppNavGraph(
    navHostController: NavHostController,
    photoScreenContent: @Composable () -> Unit,
    videoScreenContent: @Composable () -> Unit,
    albumsScreenContent: @Composable () -> Unit,
    openPhotoScreenContent: @Composable (FeedItem) -> Unit,
    openVideoScreenContent: @Composable (FeedItem) -> Unit,
    openAlbumScreenContent: @Composable (FeedItem) -> Unit,
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.Home.route
    ) {
        homeScreenNavGraph(
            photoScreenContent,
            openPhotoScreenContent
        )
        composable(Screen.Video.route) {
            videoScreenContent()
        }
        composable(Screen.Albums.route) {
            albumsScreenContent()
        }
    }
}
