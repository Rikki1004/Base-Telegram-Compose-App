package com.rikkimikki.telegramgallery.presentation.main

import androidx.compose.foundation.clickable
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.rikkimikki.telegramgallery.navigation.AppNavGraph
import com.rikkimikki.telegramgallery.navigation.rememberNavigationState
import com.rikkimikki.telegramgallery.presentation.photo.OpenPhotoScreen
import com.rikkimikki.telegramgallery.presentation.photo.PhotoScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val navigationState = rememberNavigationState()

    Scaffold(
        bottomBar = {
            BottomAppBar  {
                val navBackStackEntry by navigationState.navHostController.currentBackStackEntryAsState()

                val items = listOf(
                    NavigationItem.Home,
                    NavigationItem.Video,
                    NavigationItem.Albums
                )
                items.forEach { item ->

                    val selected = navBackStackEntry?.destination?.hierarchy?.any {
                        it.route == item.screen.route
                    } ?: false

                    NavigationBarItem(
                        selected = selected,
                        onClick = {
                            if (!selected) {
                                navigationState.navigateTo(item.screen.route)
                            }
                        },
                        icon = {
                            Icon(item.icon, contentDescription = null)
                        },
                        label = {
                            Text(text = stringResource(id = item.titleResId))
                        },
                        /*colors =
                        selectedContentColor = MaterialTheme.colorScheme.onPrimary,
                        unselectedContentColor = MaterialTheme.colorScheme.onSecondary*/
                    )
                }
            }
        }
    ) { paddingValues ->
        AppNavGraph(
            navHostController = navigationState.navHostController,
            photoScreenContent = {
                PhotoScreen(
                    paddingValues = paddingValues,
                    onPhotoClickListener = {
                        navigationState.navigateToOpenPhoto(it)
                    }
                )
            },
            openPhotoScreenContent = { feedItem ->
                OpenPhotoScreen(
                    onBackPressed = {
                        navigationState.navHostController.popBackStack()
                    },
                    feedItem = feedItem
                )
            },
            videoScreenContent = { TextCounter(name = "Video") },
            albumsScreenContent = { TextCounter(name = "Album") },
            openVideoScreenContent = { TextCounter(name = "OpenVideo") },
            openAlbumScreenContent = { TextCounter(name = "OpenAlbum") },
        )
    }
}

@Composable
private fun TextCounter(name: String) {
    var count by rememberSaveable {
        mutableStateOf(0)
    }

    Text(
        modifier = Modifier.clickable { count++ },
        text = "$name Count: $count",
        color = Color.Black
    )
}

