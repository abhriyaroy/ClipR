package studio.zebro.clipr.android.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import studio.zebro.clipr.data.repository.UserRepository

class LandingViewModel(
  private val state: SavedStateHandle,
  private val userRepository: UserRepository
) : ViewModel() {

  private val _isLoggedIn = MutableStateFlow(userRepository.isUserLoggedIn())
  val isLoggedIn: StateFlow<Boolean> = _isLoggedIn

  private val _permissionsGranted = MutableStateFlow(false)
  val permissionsGranted: StateFlow<Boolean> = _permissionsGranted.asStateFlow()

  fun updatePermissionStatus(isGranted: Boolean) {
    _permissionsGranted.value = isGranted
  }

}