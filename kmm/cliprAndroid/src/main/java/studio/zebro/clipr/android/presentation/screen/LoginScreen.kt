package studio.zebro.clipr.android.presentation.screen

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import org.koin.androidx.compose.getViewModel
import studio.zebro.clipr.android.presentation.navigation.AppNavigation
import studio.zebro.clipr.android.presentation.viewmodel.LandingViewModel

@Composable
fun LoginScreen(appNavigation: AppNavigation) {
  val loginViewModel: LandingViewModel = getViewModel()

}