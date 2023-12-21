package studio.zebro.clipr.android.presentation.permission

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

interface PermissionsManager {
  fun hasPermission(permission: String): Boolean
  fun shouldShowRequestPermissionRationale(permission: String): Boolean

  @Composable
  fun askPermissions(permission: String,
                     permissionListener: (Boolean) -> Unit,
                     rationaleMessage: String,
                     navigateToSettingsMessage: String)
}

class PermissionManagerImpl(private val context: Context) : PermissionsManager {

  override fun hasPermission(permission: String): Boolean {
    return ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED
  }

  override fun shouldShowRequestPermissionRationale(permission: String): Boolean {
    val activity = context.getActivity() ?: return false
    return ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)
  }

  @Composable
  override fun askPermissions(
    permission: String,
    permissionListener: (Boolean) -> Unit,
    rationaleMessage: String,
    navigateToSettingsMessage: String
  ) {
    val context = LocalContext.current
    var showRationale by rememberSaveable { mutableStateOf(false) }
    var showPermissionDeniedDialog by rememberSaveable { mutableStateOf(false) }
    var permissionRequested by rememberSaveable { mutableStateOf(false) }

    val permissionLauncher = rememberLauncherForActivityResult(
      ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
      if (isGranted) {
        permissionListener(isGranted)
      } else {
        val shouldShowRationale = shouldShowRequestPermissionRationale(permission)
        if (!shouldShowRationale) {
          showPermissionDeniedDialog = true
        } else {
          showRationale = true
        }
      }
    }

    LaunchedEffect(key1 = permission) {
      showRationale = permissionRequested &&
          ContextCompat.checkSelfPermission(
            context, permission
          ) != PackageManager.PERMISSION_GRANTED &&
          !shouldShowRequestPermissionRationale(permission)
    }

    if (showRationale) {
      AlertDialog(
        onDismissRequest = { showRationale = false },
        title = { Text("Permission Required") },
        text = { Text(rationaleMessage) },
        confirmButton = {
          TextButton(onClick = {
            showRationale = false
            permissionLauncher.launch(permission)
          }) {
            Text("Ok")
          }
        }
      )
    }

    if (showPermissionDeniedDialog) {
      AlertDialog(
        onDismissRequest = { showPermissionDeniedDialog = false },
        title = { Text("Permission Denied") },
        text = { Text(navigateToSettingsMessage) },
        confirmButton = {
          TextButton(onClick = {
            showPermissionDeniedDialog = false
            context.navigateToAppSettings()
          }) {
            Text("Open Settings")
          }
        }
      )
    }

    LaunchedEffect(Unit) {
      if (ContextCompat.checkSelfPermission(
          context, permission
        ) != PackageManager.PERMISSION_GRANTED
      ) {
        permissionLauncher.launch(permission)
      } else {
        permissionListener(true)
      }
    }
  }
}

fun Context.getActivity(): Activity? = when (this) {
  is Activity -> this
  is ContextWrapper -> baseContext.getActivity()
  else -> null
}

fun Context.navigateToAppSettings() {
  val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
    data = Uri.fromParts("package", packageName, null)
  }
  startActivity(intent)
}

