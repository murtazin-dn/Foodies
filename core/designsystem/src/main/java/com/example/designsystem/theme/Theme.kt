package com.example.designsystem.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val ColorsPalette = lightColorScheme(
    primary = Orange,
    secondary = Dark,
    tertiary = GrayBg
)

@Composable
fun FoodiesTheme(
    content: @Composable () -> Unit
) {
    val colorScheme = ColorsPalette

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}