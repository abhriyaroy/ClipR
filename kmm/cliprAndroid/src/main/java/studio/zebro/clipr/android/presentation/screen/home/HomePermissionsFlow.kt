package studio.zebro.clipr.android.presentation.screen.home

import android.Manifest
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import studio.zebro.clipr.android.R
import studio.zebro.clipr.android.presentation.permission.CheckPermissions
import studio.zebro.clipr.android.presentation.permission.RequestPermissions
import studio.zebro.clipr.android.presentation.viewmodel.LandingViewModel
import studio.zebro.clipr.ui.theming.Colors

@Composable
fun PermissionCard(onAllow: () -> Unit) {
  Surface(
    modifier = Modifier
      .fillMaxWidth()
      .padding(24.dp),
    shadowElevation = 8.dp,
    color = Colors.primary100,
    shape = RoundedCornerShape(24.dp)
  ) {
    Column(
      modifier = Modifier.padding(16.dp),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      Icon(
        // Replace with your actual icon vector resource
        imageVector = ImageVector.vectorResource(id = R.drawable.ic_warning),
        contentDescription = stringResource(id = R.string.permissions_required),
        modifier = Modifier.size(48.dp),
        tint = Color.Unspecified
      )
      Spacer(modifier = Modifier.height(8.dp))
      Text(
        text = stringResource(id = R.string.allow_required_permissions),
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(horizontal = 16.dp),
        textAlign = TextAlign.Center
      )
      Spacer(modifier = Modifier.height(16.dp))
      Button(
        onClick = onAllow,
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(containerColor = Colors.accent800)
      ) {
        Text(
          text = stringResource(id = R.string.allow),
          style = MaterialTheme.typography.bodyMedium
        )
      }
    }
  }
}

@Composable
fun ShowPermissionNotAvailableScreen(landingViewModel: LandingViewModel) {

  var shouldRequestPermissions by rememberSaveable { mutableStateOf(false) }
  var requestId by rememberSaveable { mutableLongStateOf(0L) }

  println("RequestPermissions recomposition $shouldRequestPermissions")
  Box(
    modifier = Modifier
      .fillMaxSize()
      .padding(start = 0.dp, top = 0.dp, end = 0.dp, bottom = 56.dp),
    contentAlignment = Alignment.Center
  ) {
    PermissionCard(
      onAllow = {
        println("RequestPermissions allow callback $shouldRequestPermissions")
        requestId = System.currentTimeMillis()
      }
    )
  }


  LaunchedEffect(key1 = requestId) {
    if (requestId != 0L) {
      shouldRequestPermissions = true
    }
  }

  if (shouldRequestPermissions) {
    println("RequestPermissions 1 $shouldRequestPermissions")
    RequestPermissions(
      requestId,
      permissions = listOf(Manifest.permission.POST_NOTIFICATIONS),
      onPermissionsResult = {
        shouldRequestPermissions = false
        println("RequestPermissions onPermissionsResult $it")
      },
      landingViewModel::hasRequestedHomePermissionsBefore,
      landingViewModel::saveDeniedPermissions
    )
  }
}
