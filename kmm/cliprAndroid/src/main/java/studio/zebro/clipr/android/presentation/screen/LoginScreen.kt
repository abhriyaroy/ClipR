package studio.zebro.clipr.android.presentation.screen

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.SpringSpec
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.updateTransition
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
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import kotlinx.coroutines.delay
import studio.zebro.clipr.android.R
import studio.zebro.clipr.android.presentation.navigation.AppNavigationRoutes
import studio.zebro.clipr.android.presentation.viewmodel.LoginViewModel
import studio.zebro.clipr.android.presentation.widgets.ButtonWithLoader
import studio.zebro.clipr.android.presentation.widgets.RoundedInputText
import studio.zebro.clipr.ui.theming.Colors

@Composable
fun LoginScreen(navHostController: NavHostController, loginViewModel: LoginViewModel) {

  val currentScreen by loginViewModel.navigateToScreen.collectAsState()

  val titleTransition = updateTransition(loginViewModel.titleSlideOut.value, null)
  val userNameTransition = updateTransition(loginViewModel.usernameSlideOut.value, null)
  val passwordTransition = updateTransition(loginViewModel.passwordSlideOut.value, null)
  val loginTransition = updateTransition(loginViewModel.loginSlideOut.value, null)
  val signupTransition = updateTransition(loginViewModel.signupSlideOut.value, null)

  val slideoutOffset = (-500).dp
  val originalOffset = 0.dp

  val titleTextFieldOffset by titleTransition.animateDp(label = "") { state ->
    if (state) slideoutOffset else originalOffset
  }

  val usernameTextFieldOffset by userNameTransition.animateDp(label = "") { state ->
    if (state) slideoutOffset else originalOffset
  }

  val passwordTextFieldOffset by passwordTransition.animateDp(label = "") { state ->
    if (state) slideoutOffset else originalOffset
  }

  val loginButtonOffset by loginTransition.animateDp(label = "") { state ->
    if (state) slideoutOffset else originalOffset
  }

  val signupButtonOffset by signupTransition.animateDp(label = "") { state ->
    if (state) slideoutOffset else originalOffset
  }

  println("the hash is - login -  ${loginViewModel.hashCode()}")

  Box(modifier = Modifier.fillMaxSize()) {
    Column(
      modifier = Modifier
        .align(Alignment.CenterStart)
        .padding(16.dp)
    ) {
      Text(
        text = stringResource(id = R.string.app_name),
        modifier = Modifier.fillMaxWidth().offset(titleTextFieldOffset),
        style = MaterialTheme.typography.displayLarge,
        color = Colors.white100
      )
      RoundedInputText(
        modifier = Modifier.offset(usernameTextFieldOffset),
        hint = stringResource(id = R.string.username_hint),
        leadingImage = Icons.Default.Person,
        maxLines = 1,
        onTextChanged = { println(this) },
      )
      Spacer(modifier = Modifier.height(8.dp))
      RoundedInputText(
        modifier = Modifier.offset(passwordTextFieldOffset),
        hint = stringResource(id = R.string.password_hint),
        leadingImage = ImageVector.vectorResource(id = R.drawable.lock),
        maxLines = 1,
        onTextChanged = { println(this) },
      )
      Spacer(modifier = Modifier.height(16.dp))
      Box(modifier = Modifier.align(Alignment.CenterHorizontally)) {
        ButtonWithLoader(
          modifier = Modifier.offset(loginButtonOffset),
          loginViewModel.isLoginLoading,
          loginViewModel.isLoginEnabled,
          text = stringResource(id = R.string.login),
          onClick = loginViewModel::handleLoginClick
        )
      }
      Spacer(modifier = Modifier.height(8.dp))
      Box(modifier = Modifier.align(Alignment.CenterHorizontally)) {
        ButtonWithLoader(
          modifier = Modifier.offset(signupButtonOffset),
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