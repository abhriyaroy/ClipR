package studio.zebro.clipr.android.presentation.widgets

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import studio.zebro.clipr.ui.theming.Colors


@Composable
fun ButtonWithLoader(
  modifier: Modifier = Modifier,
  isLoading: Boolean,
  isEnabled: Boolean,
  backgroundColor: Color = Colors.accent800,
  disabledColor: Color = Colors.accent100,
  shape: Shape = RoundedCornerShape(48.dp),
  height: Dp = 50.dp,
  width: Dp = 250.dp,
  loaderSize: Dp = 36.dp,
  text: String = "",
  onClick: () -> Unit = {},
) {
  val buttonWithLoaderTransitionLabel = "ButtonTransition"
  val buttonWithLoaderPadding = 16.dp

  val buttonContent: @Composable () -> Unit = {
    if (isLoading) {
      CircularProgressIndicator(
        modifier = Modifier.size(loaderSize),
        color = Color.White
      )
    } else {
      Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
      ) {
        Text(text = text)
      }
    }
  }


  val transition =
    updateTransition(targetState = isLoading, label = buttonWithLoaderTransitionLabel)


  val buttonWidth by transition.animateDp(label = buttonWithLoaderTransitionLabel) {
    if (it) {
      loaderSize + buttonWithLoaderPadding
    } else {
      width + buttonWithLoaderPadding
    }
  }

  Box(
    modifier = modifier
      .height(height)
      .clickable {
        if (!isEnabled) {
          return@clickable
        }
//        isLoading.value = true
//        // Simulate some background work (replace with your actual task)
//        CoroutineScope(Dispatchers.IO).launch {
//          delay(3000)
//          isLoading.value = false
//        }
        onClick()
      },
    contentAlignment = Alignment.Center
  ) {
    Box(
      modifier = Modifier
        .background(
          if (isEnabled) {
            backgroundColor
          } else {
            disabledColor
          }, shape
        )
        .height(height)
        .width(buttonWidth)
        .animateContentSize()
    )
    buttonContent()
  }
}
