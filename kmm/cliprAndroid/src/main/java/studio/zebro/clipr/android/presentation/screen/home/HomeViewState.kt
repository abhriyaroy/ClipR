package studio.zebro.clipr.android.presentation.screen.home

import studio.zebro.clipr.data.db.ClipboardDbEntity

sealed class HomeViewState {

  object Loading : HomeViewState()
  data class PermissionsMissing(val permissions: List<String>) : HomeViewState()
  object Empty : HomeViewState()
  data class CopiedItemsPresent(val items: List<ClipboardDbEntity>) : HomeViewState()

}