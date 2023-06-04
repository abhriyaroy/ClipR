package studio.zebro.clipr.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import dagger.hilt.android.AndroidEntryPoint
//import studio.zebro.clipr.android.copieditemslist.CopiedItemsListScreen

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      ClipRApp()
//      CopiedItemsListScreen()
    }
  }
}

@Composable
fun GreetingView(text: String) {
}
