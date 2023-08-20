package studio.zebro.clipr.android.di

import androidx.lifecycle.SavedStateHandle
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import studio.zebro.clipr.android.presentation.viewmodel.LandingViewModel

val appModule = module {
  viewModel<LandingViewModel> { (state: SavedStateHandle) -> LandingViewModel(state, get()) }
}