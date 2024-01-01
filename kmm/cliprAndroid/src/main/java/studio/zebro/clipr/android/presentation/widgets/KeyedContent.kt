package studio.zebro.clipr.android.presentation.widgets

import androidx.compose.runtime.Composable

@Composable
fun KeyedContent(key: Any, content: @Composable () -> Unit) {
  // This composable will only recompose if 'key' changes
  content()
}