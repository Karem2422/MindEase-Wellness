package com.mindease.wellness.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun GlassCard(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    val isDark = androidx.compose.foundation.isSystemInDarkTheme()
    
    val glassBrush = if (isDark) {
        Brush.verticalGradient(
            colors = listOf(
                Color.White.copy(alpha = 0.1f),
                Color.White.copy(alpha = 0.05f)
            )
        )
    } else {
        Brush.verticalGradient(
            colors = listOf(
                Color.White.copy(alpha = 0.7f),
                Color.White.copy(alpha = 0.4f)
            )
        )
    }
    
    val borderColor = if (isDark) Color.White.copy(alpha = 0.2f) else Color.White.copy(alpha = 0.5f)

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(24.dp))
            .background(glassBrush)
            .border(1.dp, borderColor, RoundedCornerShape(24.dp))
            .padding(16.dp)
    ) {
        Column {
            content()
        }
    }
}
