package studio.zebro.clipr.android.presentation.screen.home

import android.Manifest.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import org.koin.core.component.getScopeName
import studio.zebro.clipr.android.R
import studio.zebro.clipr.android.presentation.permission.CheckPermissions
import studio.zebro.clipr.android.presentation.permission.getPermissionsList
import studio.zebro.clipr.android.presentation.permission.RequestPermissions
import studio.zebro.clipr.android.presentation.screen.home.states.HomeScreenStateEmpty
import studio.zebro.clipr.android.presentation.service.ClipboardForegroundService
import studio.zebro.clipr.android.presentation.viewmodel.LandingViewModel
import studio.zebro.clipr.android.presentation.widgets.LifecycleEventObserver
import studio.zebro.clipr.ui.theming.Colors.primary800
import studio.zebro.clipr.ui.theming.Colors.white100

@Composable
fun HomeScreen(navController: NavHostController, landingViewModel: LandingViewModel) {

  var isViewResumed by remember { mutableStateOf(false) }
  val viewState by landingViewModel.homeViewState.collectAsState()
  var shouldShowPermissionsNotAvailableScreen by rememberSaveable { mutableStateOf(false) }
  var shouldShowEmptyScreen by rememberSaveable { mutableStateOf(false) }
  val context = LocalContext.current

  if (isViewResumed) {
    getPermissionsList().let {
      if (it.isNotEmpty()) {
        CheckPermissions(
          permissions = it,
          onPermissionsResult = landingViewModel::handleMissingPermissionsResult
        )
      }
    }
  }

  Box(
    modifier = Modifier
      .fillMaxSize()
      .background(primary800) // Light gray background for the entire view
  ) {
    Column(
      modifier = Modifier.padding(16.dp),
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.Center
    ) {
      Text(
        modifier = Modifier.fillMaxWidth(),
        text = stringResource(id = R.string.app_name),
        style = MaterialTheme.typography.displayLarge,
        color = white100
      )
      Spacer(modifier = Modifier.height(32.dp))

      LaunchedEffect(viewState) {
        shouldShowPermissionsNotAvailableScreen = false
        shouldShowEmptyScreen = false
        when (viewState) {
          is HomeViewState.PermissionsMissing -> {
            println("viewstate is PermissionsMissing")
            shouldShowPermissionsNotAvailableScreen = true
          }

          is HomeViewState.Empty -> {
            ClipboardForegroundService.startService(context = context)
            println("viewstate is Empty")
            shouldShowEmptyScreen = true
          }

          else -> {
            println("viewstate is else")
          }
        }
      }
    }
  }

  println("shouldShowPermissionsNotAvailableScreen $shouldShowPermissionsNotAvailableScreen")
  if (shouldShowPermissionsNotAvailableScreen) {
    ShowPermissionNotAvailableScreen(landingViewModel)
  }

  if (shouldShowEmptyScreen) {
    HomeScreenStateEmpty(landingViewModel)
  }

  LifecycleEventObserver(
    onResume = {
      println("LifecycleEventObserver onResume")
      isViewResumed = true
      landingViewModel.handleScreenResumed()
    },
    onPause = {
      println("LifecycleEventObserver onPause")
      isViewResumed = false
    })
}