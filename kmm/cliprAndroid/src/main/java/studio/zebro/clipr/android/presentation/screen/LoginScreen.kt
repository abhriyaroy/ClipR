package studio.zebro.clipr.android.presentation.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import org.koin.androidx.compose.getViewModel
import studio.zebro.clipr.android.R
import studio.zebro.clipr.android.presentation.viewmodel.LoginViewModel
import studio.zebro.clipr.android.presentation.widgets.ButtonWithLoader
import studio.zebro.clipr.android.presentation.widgets.RoundedInputText
import studio.zebro.clipr.ui.theming.Colors

@Composable
fun LoginScreen(navHostController: NavHostController, loginViewModel: LoginViewModel) {

  val currentScreen by loginViewModel.navigateToScreen.collectAsState()

  println("the hash is - login -  ${loginViewModel.hashCode()}")

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
          loginViewModel.isLoginLoading,
          loginViewModel.isLoginEnabled,
          text = stringResource(id = R.string.login),
          onClick = loginViewModel::handleLoginClick
        )
      }
      Spacer(modifier = Modifier.height(8.dp))
      Box(modifier = Modifier.align(Alignment.CenterHorizontally)) {
        ButtonWithLoader(
          loginViewModel.isRegisterLoading,
          loginViewModel.isRegisterEnabled,
          text = stringResource(id = R.string.register),
          onClick = loginViewModel::handleSignUpClick
        )
      }
    }
  }

  LaunchedEffect(currentScreen) {
    if (currentScreen.isNotEmpty()) {
      navHostController.navigate(currentScreen)
    }
  }

}