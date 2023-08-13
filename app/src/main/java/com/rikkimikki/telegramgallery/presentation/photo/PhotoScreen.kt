package com.rikkimikki.telegramgallery.presentation.photo

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import com.rikkimikki.telegramgallery.domain.entity.FeedItem

@Composable
fun PhotoScreen(
    paddingValues: PaddingValues, onPhotoClickListener: (FeedItem) -> Unit
) {

}
@Composable
fun OpenPhotoScreen(
    onBackPressed: () -> Unit,
    feedItem: FeedItem,
) {

}