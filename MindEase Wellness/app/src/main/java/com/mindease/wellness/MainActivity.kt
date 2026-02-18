package com.mindease.wellness

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Spa
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mindease.wellness.ui.screens.LoginScreen
import com.mindease.wellness.ui.screens.MeditationScreen
import com.mindease.wellness.ui.screens.MoodTrackerScreen
import com.mindease.wellness.ui.screens.TherapistScreen
import com.mindease.wellness.ui.theme.MindEaseWellnessTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MindEaseWellnessTheme {
                 Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainApp()
                }
            }
        }
    }
}

@Composable
fun MainApp() {
    val navController = rememberNavController()
    val items = listOf("Mood", "Meditation", "Therapy")
    val icons = listOf(Icons.Default.Home, Icons.Default.Spa, Icons.Default.Person)

    NavHost(navController = navController, startDestination = "Login") {
        composable("Login") {
            LoginScreen {
                navController.navigate("Mood") {
                    popUpTo("Login") { inclusive = true }
                }
            }
        }
        
        composable("Mood") {
            MainScaffold(navController, items, icons) { MoodTrackerScreen() }
        }
        composable("Meditation") {
            MainScaffold(navController, items, icons) { MeditationScreen() }
        }
        composable("Therapy") {
            MainScaffold(navController, items, icons) { TherapistScreen() }
        }
    }
}

@Composable
fun MainScaffold(
    navController: androidx.navigation.NavController,
    items: List<String>,
    icons: List<ImageVector>,
    content: @Composable () -> Unit
) {
    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.95f)
            ) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                items.forEachIndexed { index, screen ->
                    NavigationBarItem(
                        icon = { Icon(icons[index], contentDescription = screen) },
                        label = { Text(screen) },
                        selected = currentRoute == screen,
                        onClick = {
                            navController.navigate(screen) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            content()
        }
    }
}

