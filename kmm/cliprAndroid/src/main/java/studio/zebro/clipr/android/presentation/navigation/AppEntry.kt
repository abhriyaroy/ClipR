package studio.zebro.clipr.android.presentation.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.koin.androidx.compose.getViewModel
import studio.zebro.clipr.android.presentation.navigation.AppNavigationRoutes.HOME_SCREEN
import studio.zebro.clipr.android.presentation.navigation.AppNavigationRoutes.LOGIN_SCREEN
import studio.zebro.clipr.android.presentation.navigation.AppNavigationRoutes.SIGNUP_SCREEN
import studio.zebro.clipr.android.presentation.screen.authentication.login.LoginScreen
import studio.zebro.clipr.android.presentation.screen.authentication.signup.SignUpScreen
import studio.zebro.clipr.android.presentation.screen.home.HomeScreen
import studio.zebro.clipr.android.presentation.viewmodel.LandingViewModel
import studio.zebro.clipr.android.presentation.viewmodel.LoginViewModel
import studio.zebro.clipr.android.presentation.viewmodel.SignUpViewModel

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun App() {
  val navController = rememberNavController()

  val loginViewModel: LoginViewModel = getViewModel()
  val signUpViewModel: SignUpViewModel = getViewModel()

  val isLoggedIn = loginViewModel.isLoggedIn.collectAsState()

  NavHost(
    navController, startDestination = if (isLoggedIn.value) {
      HOME_SCREEN
    } else {
      LOGIN_SCREEN
    }
  ) {
    composable(
      route = LOGIN_SCREEN,
    ) { LoginScreen(navController) }
    composable(
      route = SIGNUP_SCREEN,
      enterTransition = null,
      exitTransition = null
    ) { SignUpScreen(navController, signUpViewModel) }
    composable(HOME_SCREEN) { HomeScreen(navController) }
  }
}