package studio.zebro.clipr.android.copieditemslist

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HideableSearchTextField(
  text: String,
  isSearchActive: Boolean,
  onTextChangeListener: (String) -> Unit,
  onSearchClickListener: () -> Unit,
  onCloseClickListener: () -> Unit,
  modifier: Modifier = Modifier
) {
  Box(modifier) {
    AnimatedVisibility(
      visible = isSearchActive,
      enter = fadeIn(),
      exit = fadeOut()
    ) {
      OutlinedTextField(
        value = text,
        onValueChange = onTextChangeListener,
        shape = RoundedCornerShape(50.dp),
        placeholder = { Text(text = "Search") },
        modifier = Modifier
          .fillMaxWidth()
          .padding(16.dp)
          .padding(end = 48.dp)
      )

      AnimatedVisibility(
        visible = isSearchActive,
        enter = fadeIn(),
        exit = fadeOut(),
        modifier = Modifier.align(Alignment.CenterEnd)
      ) {
        IconButton(onClick = onCloseClickListener) {
          Icon(imageVector = Icons.Default.Close, contentDescription = "Close search")
        }
      }

      AnimatedVisibility(
        visible = !isSearchActive,
        enter = fadeIn(),
        exit = fadeOut(),
        modifier = Modifier.align(Alignment.CenterEnd)
      ) {
        IconButton(onClick = onSearchClickListener) {
          Icon(imageVector = Icons.Default.Search, contentDescription = "Open search")
        }
      }
    }
  }
}