package studio.zebro.clipr.android.presentation.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import studio.zebro.clipr.android.presentation.screen.home.HomeViewState
import studio.zebro.clipr.data.repository.UserRepository

class LandingViewModel(
  private val state: SavedStateHandle,
  private val userRepository: UserRepository
) : ViewModel() {

  private val _homeViewState = MutableStateFlow<HomeViewState>(HomeViewState.Loading)
  val homeViewState: StateFlow<HomeViewState> = _homeViewState.asStateFlow()

  fun handleMissingPermissionsResult(missingPermissionsList: List<String>) {
    println("handleMissingPermissionsResult $missingPermissionsList")
    if (missingPermissionsList.isNotEmpty() && _homeViewState.value !is HomeViewState.PermissionsMissing) {
      _homeViewState.value = HomeViewState.PermissionsMissing(missingPermissionsList)
    } else {
      _homeViewState.value = HomeViewState.Empty
    }
  }

  fun hasRequestedHomePermissionsBefore(permissionsList: List<String>): Boolean {
    return userRepository.hasRequestedPermissionsBefore(permissionsList).apply {
      println("hasRequestedHomePermissionsBefore $this")
    }
  }

  fun saveDeniedPermissions(permissionsList: List<String>) {
    userRepository.saveDeniedPermissions(permissionsList).apply {
      println("saveDeniedPermissions $this")
    }
  }

  fun handleScreenResumed() {
    _homeViewState.value = HomeViewState.Loading
  }

}