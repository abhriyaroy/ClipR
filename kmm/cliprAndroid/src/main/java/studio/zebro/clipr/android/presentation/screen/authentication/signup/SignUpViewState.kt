package studio.zebro.clipr.android.presentation.screen.authentication.signup

sealed class SignUpViewState {
  object Loading : SignUpViewState()
  data class Success(val message: String) : SignUpViewState()
  data class Error(val errorMessage: String) : SignUpViewState()
  object Empty : SignUpViewState()
  object EnterNavigation : SignUpViewState()
  object ReturnNavigation : SignUpViewState()
  data class InputValidation(val isValid: Boolean) : SignUpViewState()
}