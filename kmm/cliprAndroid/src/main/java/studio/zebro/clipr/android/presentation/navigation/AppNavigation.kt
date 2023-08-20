package studio.zebro.clipr.android.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalView
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavHostController
import androidx.savedstate.findViewTreeSavedStateRegistryOwner

class AppNavigation(private val navController: NavHostController) {

  fun navigateToLogin() {
    navController.navigate("login")
  }

  fun navigateToSignup() {
//    navController.navigate("signup")
  }

  fun navigateToHome() {
//    navController.navigate("home")
  }
}

//@Composable
//fun rememberSavedStateHandle(): SavedStateHandle {
//  val savedStateRegistry = LocalSavedStateRegistry.current
//  val owner = LocalView.current.findViewTreeSavedStateRegistryOwner()
//  return remember(savedStateRegistry, owner) {
//    SavedStateHandleController.create(owner, savedStateRegistry).savedStateHandle
//  }
//}