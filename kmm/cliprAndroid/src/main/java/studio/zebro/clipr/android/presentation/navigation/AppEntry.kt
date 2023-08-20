package studio.zebro.clipr.android.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import studio.zebro.clipr.android.presentation.screen.LoginScreen

@Composable
fun App() {
  val navController = rememberNavController()
  val appNavigation: AppNavigation = AppNavigation(navController)

  // Set up navigation routes and Composables
  NavHost(navController, startDestination = "login") {
    composable("login") { LoginScreen(appNavigation) }
//    composable("signup") { SignupScreen(appNavigation) }
//    composable("home") { HomeScreen(appNavigation) }
  }
}