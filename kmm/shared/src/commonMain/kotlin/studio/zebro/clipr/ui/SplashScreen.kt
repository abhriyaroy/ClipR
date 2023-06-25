package studio.zebro.clipr.ui


import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.unit.dp
import dev.icerock.moko.resources.compose.painterResource
import kotlinx.coroutines.launch
import studio.zebro.clipr.sharedres
import studio.zebro.clipr.ui.theming.Colors

@Composable
fun SplashScreen() {
  Box(
    modifier = Modifier.fillMaxHeight()
      .fillMaxWidth()
      .background(Colors.primary800)
  ) {
    Column {
      showTopBar()
    }
  }
}

@Composable
fun showTopBar() {
  val animatableScale = remember { Animatable(0f) }
  val scope = rememberCoroutineScope()

  Row(
    modifier = Modifier
      .padding(16.dp, 8.dp, 16.dp, 8.dp),
    horizontalArrangement = Arrangement.SpaceBetween,
  ) {
    Image(
      painter = painterResource(sharedres.images.ic_clipr),
      contentDescription = null,
      modifier = Modifier
        .size(48.dp)
        .scale(animatableScale.value)
    )
  }

  scope.launch {
    animatableScale.animateTo(
      1f, spring(
        dampingRatio = Spring.DampingRatioHighBouncy,
        stiffness = Spring.StiffnessMedium
      )
    )
  }
}