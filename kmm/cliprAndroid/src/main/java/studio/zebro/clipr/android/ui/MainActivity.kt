package studio.zebro.clipr.android.ui

import AnimatedDotsMenu
import AnimatedText
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.koin.core.context.startKoin
import studio.zebro.clipr.ui.ClipRApp
import studio.zebro.clipr.ui.SplashScreen
import studio.zebro.clipr.ui.showTopBar
import studio.zebro.clipr.ui.theming.Colors
import studio.zebro.clipr.ui.theming.Typography

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      MaterialTheme(
        typography = Typography.ClipRTypography
      ) {
        Box(
          modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(Colors.primary800)
        ) {
          Column {
            Surface(
              modifier = Modifier.padding(16.dp), color = Colors.primary800
            ) {
              Row(
                modifier = Modifier
                  .fillMaxWidth()
                  .height(48.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
              ) {
                AnimatedText(
                  text = "ClipR",
                  textStyle = Typography.ClipRTypography.headlineLarge,
                  color = Colors.white100
                )

                AnimatedDotsMenu({})
              }
            }
          }
        }
      }
    }
  }
}