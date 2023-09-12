package studio.zebro.clipr.android.presentation.screen.authentication.login

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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import studio.zebro.clipr.android.R
import studio.zebro.clipr.android.presentation.viewmodel.LoginViewModel
import studio.zebro.clipr.android.presentation.widgets.ButtonWithLoader
import studio.zebro.clipr.android.presentation.widgets.RoundedInputText
import studio.zebro.clipr.ui.theming.Colors

@Composable
fun LoginScreen(navHostController: NavHostController, loginViewModel: LoginViewModel) {

  val currentScreen by loginViewModel.navigateToScreen.collectAsState()
  val viewState by loginViewModel.viewState.collectAsState()

  val slideOutOffset = (-500).dp
  val originalOffset = 0.dp

  val titleTransition = updateTransition(loginViewModel.loginScreenTitleSlideOut.value, null)
  val userNameTransition = updateTransition(loginViewModel.loginScreenUsernameSlideOut.value, null)
  val passwordTransition = updateTransition(loginViewModel.loginScreenPasswordSlideOut.value, null)
  val loginTransition = updateTransition(loginViewModel.loginScreenLoginButtonSlideOut.value, null)
  val signupTransition = updateTransition(loginViewModel.loginScreenSignupButtonSlideOut.value, null)

  val titleTextFieldOffset by titleTransition.animateDp(label = "") { state ->
    if (state) slideOutOffset else originalOffset
  }

  val usernameTextFieldOffset by userNameTransition.animateDp(label = "") { state ->
    if (state) slideOutOffset else originalOffset
  }

  val passwordTextFieldOffset by passwordTransition.animateDp(label = "") { state ->
    if (state) slideOutOffset else originalOffset
  }

  val loginButtonOffset by loginTransition.animateDp(label = "") { state ->
    if (state) slideOutOffset else originalOffset
  }

  val signupButtonOffset by signupTransition.animateDp(label = "") { state ->
    if (state) slideOutOffset else originalOffset
  }


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
          text = stringResource(id = R.string.signup),
          onClick = loginViewModel::handleSignUpClickInLoginScreen
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