package com.mindease.wellness.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SentimentDissatisfied
import androidx.compose.material.icons.filled.SentimentNeutral
import androidx.compose.material.icons.filled.SentimentSatisfied
import androidx.compose.material.icons.filled.SentimentVerySatisfied
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.mindease.wellness.ui.components.GlassCard

@Composable
fun MoodTrackerScreen() {
    var selectedMood by remember { mutableStateOf<String?>(null) }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "How are you feeling today?",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary
        )
        
        Spacer(modifier = Modifier.height(32.dp))
        
        GlassCard(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                MoodIcon(Icons.Default.SentimentVerySatisfied, "Great", selectedMood) { selectedMood = "Great" }
                MoodIcon(Icons.Default.SentimentSatisfied, "Good", selectedMood) { selectedMood = "Good" }
                MoodIcon(Icons.Default.SentimentNeutral, "Okay", selectedMood) { selectedMood = "Okay" }
                MoodIcon(Icons.Default.SentimentDissatisfied, "Bad", selectedMood) { selectedMood = "Bad" }
            }
        }
        
        Spacer(modifier = Modifier.height(32.dp))
        
        Text(
            text = "Weekly Trends",
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier = Modifier.height(16.dp))
        
        // Mock Chart
        GlassCard(modifier = Modifier.fillMaxWidth().height(200.dp)) {
           Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
               Text("Weekly Mood Graph Placeholder")
           }
        }
    }
}

@Composable
fun MoodIcon(icon: ImageVector, label: String, selected: String?, onClick: () -> Unit) {
    val isSelected = selected == label
    val color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
    
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable { onClick() }
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = color,
            modifier = Modifier.size(48.dp)
        )
        Text(text = label, style = MaterialTheme.typography.bodySmall, color = color)
    }
}
