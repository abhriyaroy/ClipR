package studio.zebro.clipr.android.di

import android.app.Application
import androidx.lifecycle.SavedStateHandle
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import studio.zebro.clipr.android.presentation.viewmodel.LandingViewModel
import studio.zebro.clipr.android.presentation.viewmodel.LoginViewModel
import studio.zebro.clipr.android.presentation.viewmodel.SignUpViewModel

val appModule = module {

  viewModel<LandingViewModel> { (state: SavedStateHandle) -> LandingViewModel(state, get()) }
  viewModel<LoginViewModel> { (state: SavedStateHandle) -> LoginViewModel(state, get()) }
  viewModel<SignUpViewModel> { (state: SavedStateHandle) -> SignUpViewModel(state, get()) }

//  single<PermissionsManager> { PermissionManagerImpl(get()) }

}