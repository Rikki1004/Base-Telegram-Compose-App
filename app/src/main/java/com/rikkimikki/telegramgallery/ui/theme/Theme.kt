package com.rikkimikki.telegramgallery.ui.theme

import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@SuppressLint("ConflictingOnColor")
private val darkColorPalette = darkColorScheme(
    primary = Black900,
    primaryContainer = Black900,
    secondary = Black900,
    onPrimary = Color.White,
    onSecondary = Black500
)

@SuppressLint("ConflictingOnColor")
private val lightColorPalette = lightColorScheme(
    primary = Color.White,
    primaryContainer = Color.White,
    secondary = Color.White,
    onPrimary = Black900,
    onSecondary = Black500
)

@Composable
fun TelegramGalleryTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        darkColorPalette
    } else {
        lightColorPalette
    }

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        //shapes = Shapes,
        content = content
    )
}

