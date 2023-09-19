package studio.zebro.clipr.android.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import studio.zebro.clipr.android.presentation.screen.authentication.signup.SignUpViewState
import studio.zebro.clipr.data.ResourceState
import studio.zebro.clipr.data.repository.UserRepository

class SignUpViewModel(
  private val state: SavedStateHandle,
  private val userRepository: UserRepository
) : ViewModel() {

  private val usernameKey = "signupUsernameKey"

  private val _viewState = MutableStateFlow<SignUpViewState>(SignUpViewState.Empty)
  val viewState: StateFlow<SignUpViewState> = _viewState.asStateFlow()

  private var userName: String = ""
  private var password: String = ""

  fun handleBackPress() {
    _viewState.value = SignUpViewState.ReturnNavigation
  }

  fun notifyViewCreated() {
    state.get<String>(usernameKey)?.run {
      userName = this
    }
    password = ""
    _viewState.value = SignUpViewState.EnterNavigation
  }

  fun notifyViewRemoved() {
    _viewState.value = SignUpViewState.Empty
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

  fun handleSignUpClick(){
    _viewState.value = SignUpViewState.Loading
    viewModelScope.launch(Dispatchers.IO) {
      userRepository.signUpUser(userName, password)
        .collect {
          when(it){
            is ResourceState.Loading -> {
              _viewState.value = SignUpViewState.Loading
            }
            is ResourceState.Success -> {
              _viewState.value = SignUpViewState.Success(it.data.id)
            }
            is ResourceState.Error -> {
              _viewState.value = SignUpViewState.Error("sad")
            }
          }
        }
    }
  }

  private fun validateInput() {
    viewModelScope.launch(Dispatchers.IO) {
      if ((userName.length > 3) && (password.length > 3)) {
        _viewState.value = SignUpViewState.InputValidation(true, userName, password)
      } else {
        _viewState.value = SignUpViewState.InputValidation(false, userName, password)
      }
    }
  }



}