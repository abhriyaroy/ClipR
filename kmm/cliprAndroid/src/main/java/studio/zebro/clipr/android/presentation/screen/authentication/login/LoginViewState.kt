package studio.zebro.clipr.android.presentation.screen.authentication.login

sealed class LoginViewState {
  object Loading : LoginViewState()
  data class Success(val message: String) : LoginViewState()
  data class Error(val errorMessage: String) : LoginViewState()
  object Empty : LoginViewState()
  object EnterNavigation : LoginViewState()
  object SignUpNavigation : LoginViewState()
  data class InputValidation(val isValid: Boolean) : LoginViewState()
}