package com.rikkimikki.telegramgallery.presentation.main

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.rikkimikki.telegramgallery.R
import com.rikkimikki.telegramgallery.navigation.Screen

sealed class NavigationItem(
    val screen: Screen,
    val titleResId: Int,
    val icon: ImageVector
) {
    object Home : NavigationItem(
        screen = Screen.Home,
        titleResId = R.string.navigation_item_photo,
        icon = Icons.Outlined.Home
    )

    object Video : NavigationItem(
        screen = Screen.Video,
        titleResId = R.string.navigation_item_video,
        icon = Icons.Outlined.Favorite
    )

    object Albums : NavigationItem(
        screen = Screen.Albums,
        titleResId = R.string.navigation_item_albums,
        icon = Icons.Outlined.Person
    )
}
