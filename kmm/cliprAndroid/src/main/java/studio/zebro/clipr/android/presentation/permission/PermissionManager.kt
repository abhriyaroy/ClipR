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
import androidx.compose.ui.res.stringResource
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.accompanist.permissions.shouldShowRationale
import studio.zebro.clipr.android.R
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
  onPermissionsResult: (List<String>) -> Unit
) {
  val permissionsState = rememberMultiplePermissionsState(permissions)
  val allPermissionsGranted = remember {
    permissionsState.permissions.filter { !it.status.isGranted }
  }.map {
    it.permission
  }

  LaunchedEffect(permissionsState.permissions) {
    onPermissionsResult(allPermissionsGranted)
  }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun RequestPermissions(
  requestId: Long,
  permissions: List<String>,
  onPermissionsResult: (Boolean) -> Unit,
  hasRequestedPermissionsBefore: (List<String>) -> Boolean,
  setRequestedPermissions: (List<String>) -> Unit
) {
  val context = LocalContext.current
  val permissionsState = rememberMultiplePermissionsState(permissions)

  var shouldShowPermissionDeniedMessage by remember { mutableStateOf(false) }
  var shouldShowRationale by remember { mutableStateOf(false) }
  var shouldOpenSettings by remember { mutableStateOf(false) }

  println("RequestPermissions outside $requestId")

  LaunchedEffect(requestId) {
    when {
      permissionsState.allPermissionsGranted -> {
        println("RequestPermissions allPermissionsGranted")
        onPermissionsResult(true)
      }

      permissionsState.shouldShowRationale -> {
        shouldShowRationale = true
        println("RequestPermissions should show rationale value $shouldShowRationale")
      }

      !hasRequestedPermissionsBefore(permissions) -> {
        setRequestedPermissions(permissions)
        permissionsState.launchMultiplePermissionRequest()
      }

      else -> {
        permissionsState.permissions.forEach { perm ->
          if (!perm.status.shouldShowRationale && !perm.status.isGranted) {
            println("RequestPermissions should show denied ${perm.permission} set to ${perm.status.shouldShowRationale} and ${perm.status.isGranted}")
            shouldShowPermissionDeniedMessage = true
            return@LaunchedEffect
          }
        }
        permissionsState.launchMultiplePermissionRequest()
      }
    }
  }

  println("shouldShowRationale111 $shouldShowRationale")
  if (shouldShowRationale) {
    CustomMaterialAlertDialog(
      stringResource(id = R.string.permissions_denied_once_title),
      stringResource(id = R.string.permission_denied_once_message),
      stringResource(id = R.string.ok_caps),
      stringResource(id = R.string.cancel_caps),
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
    CustomMaterialAlertDialog(
      stringResource(id = R.string.permissions_denied_permanently_title),
      stringResource(id = R.string.permission_denied_permanently_message),
      stringResource(id = R.string.ok_caps),
      stringResource(id = R.string.cancel_caps),
      onConfirm = {
        shouldOpenSettings = true
        shouldShowPermissionDeniedMessage = false
      },
      onCancel = {
        shouldOpenSettings = false
        shouldShowPermissionDeniedMessage = false
      }
    )
  }

  if (shouldOpenSettings) {
    OpenAppSettings()
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
