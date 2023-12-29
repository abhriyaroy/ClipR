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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import studio.zebro.clipr.android.R
import studio.zebro.clipr.android.presentation.permission.CheckPermissions
import studio.zebro.clipr.android.presentation.permission.getPermissionsList
import studio.zebro.clipr.android.presentation.permission.RequestPermissions
import studio.zebro.clipr.android.presentation.viewmodel.LandingViewModel
import studio.zebro.clipr.ui.theming.Colors.primary800
import studio.zebro.clipr.ui.theming.Colors.white100

@Composable
fun HomeScreen(navController: NavHostController, landingViewModel: LandingViewModel) {

  val shouldShowPermissionsNotAvailableScreen by landingViewModel.permissionsGranted.collectAsState()


  getPermissionsList().let {
    if (it.isNotEmpty()) {
      CheckPermissions(
        permissions = it,
        onPermissionsResult = landingViewModel::updatePermissionStatus
      )
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
      if (!shouldShowPermissionsNotAvailableScreen) {
        ShowPermissionNotAvailableScreen()
      }
    }
  }
}

@Composable
fun ShowPermissionNotAvailableScreen() {

  var shouldRequestPermissions by remember { mutableStateOf(false) }

  Box(
    modifier = Modifier
      .fillMaxSize()
      .padding(start = 0.dp, top = 0.dp, end = 0.dp, bottom = 56.dp),
    contentAlignment = Alignment.Center
  ) {
    PermissionCard(
      onAllow = {
      println("the current state is $shouldRequestPermissions")
        shouldRequestPermissions = true }
    )
  }

  if (shouldRequestPermissions) {
    RequestPermissions(
      permissions = listOf(permission.POST_NOTIFICATIONS),
      onPermissionsResult = {
        shouldRequestPermissions = false
      },
    )
  }
}