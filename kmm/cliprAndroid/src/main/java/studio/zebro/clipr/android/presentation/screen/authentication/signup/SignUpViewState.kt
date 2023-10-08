package studio.zebro.clipr.android.presentation.screen.authentication.signup

import studio.zebro.clipr.data.exception.BaseException

sealed class SignUpViewState {
  object Loading : SignUpViewState()
  data class Success(val message: String) : SignUpViewState()
  data class Error(val error: BaseException?) : SignUpViewState()
  object Empty : SignUpViewState()
  object EnterNavigation : SignUpViewState()
  object ReturnNavigation : SignUpViewState()
  data class InputValidation(val isValid: Boolean, val userName: String, val password: String) : SignUpViewState()
}