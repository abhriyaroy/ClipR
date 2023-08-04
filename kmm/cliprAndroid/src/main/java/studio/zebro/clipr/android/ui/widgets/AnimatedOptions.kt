import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun AnimatedDotsMenu(onClick: () -> Unit) {
  val interactionSource = remember { MutableInteractionSource() }

  val dotDuration = 1000 // Duration for each dot animation in milliseconds

  val showDots = remember { mutableStateOf(false) }

  // Trigger animations after some delay
  LaunchedEffect(key1 = true) {
    delay(1000)
    showDots.value = true
  }

  Box(
    modifier = Modifier
      .clickable(
      indication = rememberRipple(bounded = false),
      interactionSource = interactionSource,
      onClick = onClick)
      .padding(8.dp) // Add padding to increase clickable area
  ) {
    Row(horizontalArrangement = Arrangement.End) {
      Dot(visible = showDots.value, durationMillis = dotDuration)
      Spacer(Modifier.width(4.dp))
      Dot(visible = showDots.value, durationMillis = dotDuration)
      Spacer(Modifier.width(4.dp))
      Dot(visible = showDots.value, durationMillis = dotDuration)
    }
  }
}

@Composable
fun Dot(visible: Boolean, durationMillis: Int) {
  AnimatedVisibility(
    visible = visible,
    enter = slideInHorizontally(initialOffsetX = { it + 100 }, animationSpec = tween(durationMillis)),
    exit = slideOutHorizontally(targetOffsetX = { -it }, animationSpec = tween(durationMillis))
  ) {
    Box(
      modifier = Modifier
        .size(4.dp)
        .background(Color.White, shape = CircleShape)
    )
  }
}
