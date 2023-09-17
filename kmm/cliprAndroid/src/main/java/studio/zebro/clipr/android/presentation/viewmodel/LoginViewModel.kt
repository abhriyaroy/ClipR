package studio.zebro.clipr.android.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import studio.zebro.clipr.android.presentation.screen.authentication.login.LoginViewState
import studio.zebro.clipr.data.repository.UserRepository

class LoginViewModel(
  private val state: SavedStateHandle,
  private val userRepository: UserRepository
) : ViewModel() {

  private val usernameKey = "usernameKey"

  private val _viewState = MutableStateFlow<LoginViewState>(LoginViewState.Empty)
  val viewState: StateFlow<LoginViewState> = _viewState.asStateFlow()

  private var userName: String = ""
  private var password: String = ""

  fun notifyViewCreated() {
    state.get<String>(usernameKey)?.run {
      userName = this
    }
    password = ""
    println("LoginScreen: notifyViewCreated $userName $password")
    _viewState.value = LoginViewState.EnterNavigation
    validateInput(true)
  }

  fun notifyViewRemoved() {
    _viewState.value = LoginViewState.Empty
  }

  fun handleUserNameInput(userName: String) {
    this.userName = userName
    state[usernameKey] = userName
    validateInput()
  }

  fun handlePasswordInput(password: String) {
    this.password = password
    validateInput()
  }

  fun handleLoginClick() {
  }

  fun handleSignUpClickInLoginScreen() {
    _viewState.value = LoginViewState.SignUpNavigation
  }

  private fun validateInput(shouldDelay: Boolean = false) {
    viewModelScope.launch(Dispatchers.IO) {
      if(shouldDelay) {
        delay(300)
      }
      println("LoginScreen: validateInput $userName $password ${((userName.length > 3) && (password.length > 3))}")
      if ((userName.length > 3) && (password.length > 3)) {
        _viewState.value = LoginViewState.InputValidation(true, userName, password)
      } else {
        _viewState.value = LoginViewState.InputValidation(false, userName, password)
      }
    }
  }

}