package studio.zebro.clipr.android.presentation.screen

import android.graphics.drawable.VectorDrawable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import org.koin.androidx.compose.getViewModel
import studio.zebro.clipr.android.R
import studio.zebro.clipr.android.presentation.navigation.AppNavigation
import studio.zebro.clipr.android.presentation.viewmodel.LandingViewModel
import studio.zebro.clipr.android.presentation.widgets.ButtonWithLoader
import studio.zebro.clipr.android.presentation.widgets.RoundedInputText
import studio.zebro.clipr.ui.theming.Colors

@Composable
fun LoginScreen(appNavigation: AppNavigation) {

  val loginViewModel: LandingViewModel = getViewModel()
  val isLoading = remember {
    mutableStateOf(false)
  }
  val isEnable = remember {
    mutableStateOf(true)
  }

  Box(modifier = Modifier.fillMaxSize()) {
    Column(
      modifier = Modifier
        .align(Alignment.CenterStart)
        .padding(16.dp)
    ) {
      Text(
        text = stringResource(id = R.string.app_name),
        modifier = Modifier.fillMaxWidth(),
        style = MaterialTheme.typography.displayLarge,
        color = Colors.white100
      )
      RoundedInputText(
        hint = stringResource(id = R.string.username_hint),
        leadingImage = Icons.Default.Person,
        maxLines = 1,
        onTextChanged = { println(this) },
      )
      Spacer(modifier = Modifier.height(8.dp))
      RoundedInputText(
        hint = stringResource(id = R.string.password_hint),
        leadingImage = ImageVector.vectorResource(id = R.drawable.lock),
        maxLines = 1,
        onTextChanged = { println(this) },
      )
      Spacer(modifier = Modifier.height(16.dp))
      Box(modifier = Modifier.align(Alignment.CenterHorizontally)) {
        ButtonWithLoader(
          isLoading,
          isEnable,
          text = stringResource(id = R.string.login),
        )
      }
    }
  }

}