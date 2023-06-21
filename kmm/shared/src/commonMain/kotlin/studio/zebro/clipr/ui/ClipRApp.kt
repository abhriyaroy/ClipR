package studio.zebro.clipr.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import studio.zebro.clipr.ui.theming.Typography.ClipRTypography

@Composable
fun ClipRApp() {
  MaterialTheme(
    typography = ClipRTypography
  ) {
    SplashScreen()
  }
}