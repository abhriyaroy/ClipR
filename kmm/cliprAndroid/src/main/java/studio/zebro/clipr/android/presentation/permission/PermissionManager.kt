package studio.zebro.clipr.android.presentation.permission

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.accompanist.permissions.shouldShowRationale
import studio.zebro.clipr.android.presentation.widgets.CustomMaterialAlertDialog

fun getPermissionsList() = mutableListOf<String>().apply {
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
    add(Manifest.permission.POST_NOTIFICATIONS)
  }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun CheckPermissions(
  permissions: List<String>,
  onPermissionsResult: (Boolean) -> Unit
) {
  val permissionsState = rememberMultiplePermissionsState(permissions)
  val allPermissionsGranted = remember {
    permissionsState.permissions.all { it.status.isGranted }
  }

  LaunchedEffect(permissionsState.permissions) {
    onPermissionsResult(allPermissionsGranted)
  }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun RequestPermissions(
  permissions: List<String>,
  onPermissionsResult: (Boolean) -> Unit,
) {
  val context = LocalContext.current
  val permissionsState = rememberMultiplePermissionsState(permissions)

  var shouldShowPermissionDeniedMessage by remember { mutableStateOf(false) }
  var shouldShowRationale by remember { mutableStateOf(false) }

  LaunchedEffect(permissionsState.permissions) {
    shouldShowPermissionDeniedMessage = false
    shouldShowRationale = false
    when {
      permissionsState.allPermissionsGranted -> {
        onPermissionsResult(true)
      }

      permissionsState.shouldShowRationale -> {
        shouldShowRationale = true
      }

      else -> {
        permissionsState.permissions.forEach { perm ->
          if (!perm.status.shouldShowRationale && !perm.status.isGranted) {
            shouldShowPermissionDeniedMessage = true
            return@LaunchedEffect
          }
        }
        permissionsState.launchMultiplePermissionRequest()
      }
    }
  }

  if (shouldShowRationale) {
    CustomMaterialAlertDialog(
      "Permissions are required",
      "Looks like you have previously denied some of the permissions, but these are essential for ClipR to function. Hence please click on Proceed to grant them",
      onConfirm = {
        permissionsState.launchMultiplePermissionRequest()
        shouldShowRationale = false
      },
      onCancel = {
        shouldShowRationale = false
        onPermissionsResult(false)
      }
    )
  }

  if (shouldShowPermissionDeniedMessage) {
    OpenAppSettings()
    shouldShowPermissionDeniedMessage = false
  }
}

@Composable
fun OpenAppSettings() {
  val context = LocalContext.current

  val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
    data = Uri.fromParts("package", context.packageName, null)
  }

  context.startActivity(intent)
}
