package studio.zebro.clipr.android.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
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

  var titleSlideOut = mutableStateOf(false)
  var usernameSlideOut = mutableStateOf(false)
  var passwordSlideOut = mutableStateOf(false)
  var loginSlideOut = mutableStateOf(false)
  var signupSlideOut = mutableStateOf(false)

  fun handleLoginClick() {
//        userRepository.login()
  }

  fun handleSignUpClick() {
    if (!isRegisterEnabled.value) return
    CoroutineScope(Dispatchers.IO).launch {
      titleSlideOut.value = true
      delay(50)
      usernameSlideOut.value = true
      delay(50)
      passwordSlideOut.value = true
      delay(50)
      loginSlideOut.value = true
      delay(50)
      signupSlideOut.value = true
      delay(100)
      _navigateToScreen.value = AppNavigationRoutes.SIGNUP_SCREEN_NAME
    }
  }

  fun handleBackClickFromSignUp() {
    CoroutineScope(Dispatchers.IO).launch {
      titleSlideOut.value = false
      delay(50)
      usernameSlideOut.value = false
      delay(50)
      passwordSlideOut.value = false
      delay(50)
      loginSlideOut.value = false
      delay(50)
      signupSlideOut.value = false
    }
  }

  fun updateNavigatedToSignUpScreen() {
    _navigateToScreen.value = AppNavigationRoutes.EMPTY
  }
}