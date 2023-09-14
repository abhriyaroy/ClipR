package studio.zebro.clipr.android.presentation.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.koin.androidx.compose.getViewModel
import studio.zebro.clipr.android.presentation.navigation.AppNavigationRoutes.LOGIN_SCREEN
import studio.zebro.clipr.android.presentation.navigation.AppNavigationRoutes.SIGNUP_SCREEN
import studio.zebro.clipr.android.presentation.screen.authentication.login.LoginScreen
import studio.zebro.clipr.android.presentation.screen.authentication.signup.SignUpScreen
import studio.zebro.clipr.android.presentation.viewmodel.LoginViewModel
import studio.zebro.clipr.android.presentation.viewmodel.SignUpViewModel

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun App() {
  val navController = rememberNavController()

  val loginViewModel: LoginViewModel = getViewModel()
  val signUpViewModel: SignUpViewModel = getViewModel()

  // Set up navigation routes and Composables
  NavHost(navController, startDestination = LOGIN_SCREEN) {
    composable(
      route = LOGIN_SCREEN,
    ) { LoginScreen(navController) }
    composable(
      route = SIGNUP_SCREEN,
      enterTransition = null,
      exitTransition = null
    ) { SignUpScreen(navController, signUpViewModel) }
//    composable("home") { HomeScreen(appNavigation) }
  }
}