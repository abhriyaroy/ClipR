import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun AnimatedText(text: String, textStyle: TextStyle, modifier: Modifier = Modifier, color: Color) {
  val coroutineScope = rememberCoroutineScope()
  val delayTime = 250L  // delay in milliseconds for each character
  val textList = remember { mutableStateListOf<Char>() }

  LaunchedEffect(key1 = text) {
    textList.clear()

    coroutineScope.launch {
      text.forEach { character ->
        delay(delayTime)
        textList.add(character)
      }
    }
  }

  Text(
    text = textList.joinToString(separator = ""),
    modifier = modifier,
    style = textStyle,
    color = color,
  )
}

