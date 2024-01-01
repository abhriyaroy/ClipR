package studio.zebro.clipr.android.presentation.widgets

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import studio.zebro.clipr.ui.theming.Colors

@Composable
fun CustomMaterialAlertDialog(
  title: String,
  message: String,
  confirmMessage : String ,
  cancelMessage : String,
  onConfirm: () -> Unit,
  onCancel: () -> Unit
) {
  Dialog(
    onDismissRequest = { },
    properties = DialogProperties(usePlatformDefaultWidth = true)
  ) {
    Surface(
      shape = MaterialTheme.shapes.medium,
      color = Colors.primary100
    ) {
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .padding(vertical = 24.dp, horizontal = 24.dp)
      ) {
        Text(
          text = title,
          style = MaterialTheme.typography.bodyLarge,
          color = Colors.white100
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
          text = message,
          style = MaterialTheme.typography.bodyMedium,
          color = Colors.white100
        )
        Spacer(modifier = Modifier.height(24.dp))
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.End
        ) {
          TextButton(
            onClick = {
              onCancel()
            }
          ) {
            Text(cancelMessage, color = Colors.white100)
          }
          Button(
            onClick = {
              onConfirm()
            },
            colors = ButtonDefaults.buttonColors(containerColor = Colors.accent800)
          ) {
            Text(confirmMessage, color = Colors.white100)
          }
        }
      }
    }
  }
}
