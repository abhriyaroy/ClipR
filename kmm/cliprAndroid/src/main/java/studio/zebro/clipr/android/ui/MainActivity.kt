package studio.zebro.clipr.android.ui

import AnimatedDotsMenu
import AnimatedText
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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
              modifier = Modifier
                .padding(16.dp), color = Colors.primary800,
            ) {
              Row(
                modifier = Modifier
                  .fillMaxWidth()
                  .wrapContentHeight(),
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