package studio.zebro.clipr.android.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

  private val _viewState = MutableStateFlow<LoginViewState>(LoginViewState.Empty)
  val viewState: StateFlow<LoginViewState> = _viewState.asStateFlow()

  fun notifyViewCreated() {
    _viewState.value = LoginViewState.EnterNavigation
  }

  fun notifyViewRemoved() {
    _viewState.value = LoginViewState.Empty
  }

  fun handleLoginClick() {
  }

  fun handleSignUpClickInLoginScreen() {
    if (!isRegisterEnabled.value) return
    _viewState.value = LoginViewState.SignUpNavigation

  }

}