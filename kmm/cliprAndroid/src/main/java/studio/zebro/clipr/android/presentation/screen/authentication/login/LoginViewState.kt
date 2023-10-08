package studio.zebro.clipr.android.presentation.screen.authentication.login

import studio.zebro.clipr.data.exception.BaseException

sealed class LoginViewState {
  object Loading : LoginViewState()
  data class LoginSuccess(val message: String) : LoginViewState()
  data class LoginError(val error : BaseException?) : LoginViewState()
  object Empty : LoginViewState()
  object EnterNavigation : LoginViewState()
  object SignUpNavigation : LoginViewState()
  data class InputValidation(val isValid: Boolean, val userName: String, val password: String) :
    LoginViewState()
}