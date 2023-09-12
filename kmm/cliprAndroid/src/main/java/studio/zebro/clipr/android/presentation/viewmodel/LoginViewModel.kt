package studio.zebro.clipr.android.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import studio.zebro.clipr.android.presentation.navigation.AppNavigationRoutes
import studio.zebro.clipr.android.presentation.screen.authentication.login.LoginViewState
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

  var loginScreenTitleSlideOut = mutableStateOf(false)
  var loginScreenUsernameSlideOut = mutableStateOf(false)
  var loginScreenPasswordSlideOut = mutableStateOf(false)
  var loginScreenLoginButtonSlideOut = mutableStateOf(false)
  var loginScreenSignupButtonSlideOut = mutableStateOf(false)

  private val _viewState = MutableStateFlow<LoginViewState>(LoginViewState.Empty)
  val viewState: StateFlow<LoginViewState> = _viewState.asStateFlow()


  fun handleLoginClick() {
  }

  fun handleSignUpClickInLoginScreen() {
    if (!isRegisterEnabled.value) return
//    resetSignUpScreenState()
    exitLoginScreenToSignupScreen()
  }

  fun handleBackClickFromSignUp() {
    reenterLoginScreenWithDelay()
  }

//  fun updateNavigatedToSignUpScreen() {
//    _navigateToScreen.value = AppNavigationRoutes.EMPTY
//    CoroutineScope(Dispatchers.IO).launch {
//      singUpScreentitleSlideOut.value = true
//      delay(50)
//      singUpScreenUsernameSlideOut.value = true
//      delay(50)
//      singUpScreenPasswordSlideOut.value = true
//      delay(50)
//      singUpScreenSignupButtonSlideOut.value = true
//    }
//  }

  fun handleSignUpClickInSignUpScreen() {

  }

//  private fun resetSignUpScreenState() {
//    singUpScreentitleSlideOut.value = false
//    singUpScreenUsernameSlideOut.value = false
//    singUpScreenPasswordSlideOut.value = false
//    singUpScreenSignupButtonSlideOut.value = false
//  }

  private fun exitLoginScreenToSignupScreen() {
    CoroutineScope(Dispatchers.IO).launch {
      exitLoginScreen()
      delay(100)
      _navigateToScreen.value = AppNavigationRoutes.SIGNUP_SCREEN_NAME
    }
  }

  private fun reenterLoginScreenWithDelay() {
    CoroutineScope(Dispatchers.IO).launch {
      delay(400)
      loginScreenTitleSlideOut.value = false
      delay(50)
      loginScreenUsernameSlideOut.value = false
      delay(50)
      loginScreenPasswordSlideOut.value = false
      delay(50)
      loginScreenLoginButtonSlideOut.value = false
      delay(50)
      loginScreenSignupButtonSlideOut.value = false
    }
  }

  private suspend fun exitLoginScreen() {
    loginScreenTitleSlideOut.value = true
    delay(50)
    loginScreenUsernameSlideOut.value = true
    delay(50)
    loginScreenPasswordSlideOut.value = true
    delay(50)
    loginScreenLoginButtonSlideOut.value = true
    delay(50)
    loginScreenSignupButtonSlideOut.value = true
  }
}