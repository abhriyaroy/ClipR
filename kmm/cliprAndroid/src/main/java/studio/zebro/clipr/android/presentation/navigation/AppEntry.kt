package studio.zebro.clipr.android.presentation.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.koin.androidx.compose.getViewModel
import studio.zebro.clipr.android.presentation.navigation.AppNavigationRoutes.LOGIN_SCREEN_NAME
import studio.zebro.clipr.android.presentation.navigation.AppNavigationRoutes.SIGNUP_SCREEN_NAME
import studio.zebro.clipr.android.presentation.screen.LoginScreen
import studio.zebro.clipr.android.presentation.screen.SignUpScreen
import studio.zebro.clipr.android.presentation.viewmodel.LoginViewModel

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun App() {
  val navController = rememberNavController()

  val loginViewModel: LoginViewModel = getViewModel()

  // Set up navigation routes and Composables
  NavHost(navController, startDestination = LOGIN_SCREEN_NAME) {
    composable(
      route = LOGIN_SCREEN_NAME,
    ) { LoginScreen(navController, loginViewModel) }
    composable(
      route = SIGNUP_SCREEN_NAME,
      enterTransition = null,
      exitTransition = null
    ) { SignUpScreen(navController, loginViewModel) }
//    composable("home") { HomeScreen(appNavigation) }
  }
}