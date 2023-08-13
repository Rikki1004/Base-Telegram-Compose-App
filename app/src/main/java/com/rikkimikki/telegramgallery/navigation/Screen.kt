package com.rikkimikki.telegramgallery.navigation

import android.net.Uri
import com.google.gson.Gson
import com.rikkimikki.telegramgallery.domain.entity.FeedItem

sealed class Screen(
    val route: String
) {

    object Photo : Screen(ROUTE_PHOTO)
    object Video : Screen(ROUTE_VIDEO)
    object Albums : Screen(ROUTE_ALBUMS)
    object Home : Screen(ROUTE_HOME)

    object OpenPhoto : Screen(ROUTE_OPEN_PHOTO) {
        private const val ROUTE_FOR_ARGS = "openPhoto"
        fun getRouteWithArgs(feedPost: FeedItem): String {
            val feedPostJson = Gson().toJson(feedPost)
            return "$ROUTE_FOR_ARGS/${feedPostJson.encode()}"
        }
    }

    object OpenVideo : Screen(ROUTE_OPEN_VIDEO) {
        private const val ROUTE_FOR_ARGS = "openVideo"
        fun getRouteWithArgs(feedPost: FeedItem): String {
            val feedPostJson = Gson().toJson(feedPost)
            return "$ROUTE_FOR_ARGS/${feedPostJson.encode()}"
        }
    }

    object OpenAlbum : Screen(ROUTE_OPEN_ALBUMS) {
        private const val ROUTE_FOR_ARGS = "openAlbum"
        fun getRouteWithArgs(feedPost: FeedItem): String {
            val feedPostJson = Gson().toJson(feedPost)
            return "$ROUTE_FOR_ARGS/${feedPostJson.encode()}"
        }
    }

    companion object {

        const val KEY_FEED_ITEM = "feed_item"

        const val ROUTE_HOME = "home"
        const val ROUTE_OPEN_PHOTO = "openPhoto/{$KEY_FEED_ITEM}"
        const val ROUTE_OPEN_VIDEO = "openVideo/{$KEY_FEED_ITEM}"
        const val ROUTE_OPEN_ALBUMS = "openAlbum/{$KEY_FEED_ITEM}"
        const val ROUTE_PHOTO = "photo"
        const val ROUTE_VIDEO = "video"
        const val ROUTE_ALBUMS = "albums"
    }
}

fun String.encode(): String {
    return Uri.encode(this)
}
