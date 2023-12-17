package studio.zebro.clipr.android.presentation.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import studio.zebro.clipr.android.R
import studio.zebro.clipr.ui.theming.Colors
import studio.zebro.clipr.ui.theming.Colors.accent800
import studio.zebro.clipr.ui.theming.Colors.primary100
import studio.zebro.clipr.ui.theming.Colors.primary800

@Composable
fun PermissionCard(onAllow: () -> Unit) {
  Surface(
    modifier = Modifier
      .fillMaxWidth()
      .padding(24.dp),
    shadowElevation = 8.dp,
    color = primary100,
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
        colors = ButtonDefaults.buttonColors(containerColor = accent800)
      ) {
        Text(text = stringResource(id = R.string.allow),
          style = MaterialTheme.typography.bodyMedium
        )
      }
    }
  }
}