package studio.zebro.clipr.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import org.koin.core.context.startKoin
import studio.zebro.clipr.android.di.myModule
import studio.zebro.clipr.ui.ClipRApp
import studio.zebro.clipr.ui.SplashScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startKoin {
            modules(myModule)
        }
        setContent {
            ClipRApp()
        }
    }
}