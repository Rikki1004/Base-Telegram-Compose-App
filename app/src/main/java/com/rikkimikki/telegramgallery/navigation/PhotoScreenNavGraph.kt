package com.rikkimikki.telegramgallery.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.rikkimikki.telegramgallery.domain.entity.FeedItem

fun NavGraphBuilder.homeScreenNavGraph(
    photoScreenContent: @Composable () -> Unit,
    openPhotoScreenContent: @Composable (FeedItem) -> Unit
) {
    navigation(
        startDestination = Screen.Photo.route,
        route = Screen.Home.route
    ) {
        composable(Screen.Photo.route) {
            photoScreenContent()
        }
        composable(
            route = Screen.OpenPhoto.route,
            arguments = listOf(
                navArgument(Screen.KEY_FEED_ITEM) {
                    type = FeedItem.NavigationType
                }
            )
        ) {
            val feedPost = it.arguments?.getParcelable<FeedItem>(Screen.KEY_FEED_ITEM)
                ?: throw RuntimeException("Args is null")
            openPhotoScreenContent(feedPost)
        }
    }
}
