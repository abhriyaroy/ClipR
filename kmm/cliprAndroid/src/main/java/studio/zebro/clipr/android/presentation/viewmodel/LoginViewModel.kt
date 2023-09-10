package studio.zebro.clipr.android.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import studio.zebro.clipr.android.presentation.navigation.AppNavigationRoutes
import studio.zebro.clipr.data.repository.UserRepository

class LoginViewModel(
  private val state: SavedStateHandle,
  private val userRepository: UserRepository
) : ViewModel() {

  val isLoginLoading = mutableStateOf(false)
  val isLoginEnabled = mutableStateOf(true)

  val isRegisterLoading = mutableStateOf(false)
  val isRegisterEnabled = mutableStateOf(true)

  private val _navigateToScreen = MutableStateFlow(AppNavigationRoutes.EMPTY)
  val navigateToScreen: StateFlow<String> = _navigateToScreen

  fun handleLoginClick() {
//        userRepository.login()
  }

  fun handleSignUpClick() {
    if (!isRegisterEnabled.value) return
    _navigateToScreen.value = AppNavigationRoutes.SIGNUP_SCREEN_NAME
  }

  fun handleBackClickFromSignUp() {
    _navigateToScreen.value = AppNavigationRoutes.SIGNUP_SCREEN_NAME
  }

  fun updateNavigatedToSignUpScreen() {
    _navigateToScreen.value = AppNavigationRoutes.EMPTY
  }
}