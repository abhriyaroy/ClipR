package studio.zebro.clipr.android.ui.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun ClickableBoxWithRipple(
  size: Dp,
  contentAlignment: Alignment,
  onclick: () -> Unit,
  content: @Composable () -> Unit
) {
  val interactionSource = remember { MutableInteractionSource() }
  Box(
    modifier = Modifier
      .size(size)
      .clickable(interactionSource = interactionSource, indication = null, onClick = onclick)
      .indication(interactionSource, rememberRipple(bounded = false)),
    contentAlignment = contentAlignment,
  ) {
    content()
  }
}

@Preview(showBackground = true)
@Composable
fun PreviewClickableBoxWithRipple() {
  MaterialTheme {
    ClickableBoxWithRipple(100.dp, Alignment.Center, {}) {}
  }
}


