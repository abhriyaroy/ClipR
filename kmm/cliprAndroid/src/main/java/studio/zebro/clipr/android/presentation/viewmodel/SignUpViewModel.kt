package studio.zebro.clipr.android.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import studio.zebro.clipr.android.presentation.screen.authentication.signup.SignUpViewState
import studio.zebro.clipr.data.repository.UserRepository

class SignUpViewModel(
  private val state: SavedStateHandle,
  private val userRepository: UserRepository
) : ViewModel() {

  private val _viewState = MutableStateFlow<SignUpViewState>(SignUpViewState.Empty)
  val viewState: StateFlow<SignUpViewState> = _viewState.asStateFlow()

  fun handleBackPress() {
    _viewState.value = SignUpViewState.ReturnNavigation
  }

  fun notifyViewCreated() {
    _viewState.value = SignUpViewState.EnterNavigation
  }

  fun notifyViewRemoved() {
    _viewState.value = SignUpViewState.Empty
  }

}