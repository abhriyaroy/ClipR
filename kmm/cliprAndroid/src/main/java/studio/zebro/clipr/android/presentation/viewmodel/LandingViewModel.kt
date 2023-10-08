package studio.zebro.clipr.android.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import studio.zebro.clipr.data.repository.UserRepository

class LandingViewModel(
  private val state: SavedStateHandle,
  private val userRepository: UserRepository
) : ViewModel() {

  private val _isLoggedIn = MutableStateFlow(userRepository.isUserLoggedIn())
  val isLoggedIn: StateFlow<Boolean> = _isLoggedIn

}