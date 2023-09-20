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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel
import studio.zebro.clipr.android.R
import studio.zebro.clipr.android.presentation.navigation.AppNavigationRoutes
import studio.zebro.clipr.android.presentation.viewmodel.LoginViewModel
import studio.zebro.clipr.android.presentation.widgets.ButtonWithLoader
import studio.zebro.clipr.android.presentation.widgets.RoundedInputText
import studio.zebro.clipr.ui.theming.Colors

private var loginScreenTitleSlideOut = mutableStateOf(false)
private var loginScreenUsernameSlideOut = mutableStateOf(false)
private var loginScreenPasswordSlideOut = mutableStateOf(false)
private var loginScreenLoginButtonSlideOut = mutableStateOf(false)
private var loginScreenSignupButtonSlideOut = mutableStateOf(false)

@Composable
fun LoginScreen(navHostController: NavHostController) {

  val loginViewModel: LoginViewModel = getViewModel()
  val viewState by loginViewModel.viewState.collectAsState()

  val inputUserName = remember {
    mutableStateOf("")
  }
  val inputPassword = remember {
    mutableStateOf("")
  }

  val shouldEnableLoginButton = remember { mutableStateOf(false) }
  val isLoading = remember { mutableStateOf(false) }

  val isPasswordHidden = remember { mutableStateOf(true) }

  val slideOutOffset = (-5000).dp
  val originalOffset = 0.dp

  val titleTransition = updateTransition(loginScreenTitleSlideOut.value, null)
  val userNameTransition = updateTransition(loginScreenUsernameSlideOut.value, null)
  val passwordTransition = updateTransition(loginScreenPasswordSlideOut.value, null)
  val loginTransition = updateTransition(loginScreenLoginButtonSlideOut.value, null)
  val signupTransition = updateTransition(loginScreenSignupButtonSlideOut.value, null)

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
        modifier = Modifier
          .fillMaxWidth()
          .offset(titleTextFieldOffset),
        style = MaterialTheme.typography.displayLarge,
        color = Colors.white100
      )
      RoundedInputText(
        modifier = Modifier.offset(usernameTextFieldOffset),
        initialValue = inputUserName,
        hint = stringResource(id = R.string.username_hint),
        leadingImage = Icons.Default.Person,
        maxLines = 1,
        onTextChanged = {
          loginViewModel.handleUserNameInput(it)
        },
      )
      Spacer(modifier = Modifier.height(8.dp))
      RoundedInputText(
        modifier = Modifier.offset(passwordTextFieldOffset),
        initialValue = inputPassword,
        hint = stringResource(id = R.string.password_hint),
        leadingImage = ImageVector.vectorResource(id = R.drawable.lock),
        maxLines = 1,
        onTextChanged = {
          loginViewModel.handlePasswordInput(it)
        },
        isPasswordField = true,
        shouldHideInput = isPasswordHidden.value,
        onHideToggleClick = {
          isPasswordHidden.value = !isPasswordHidden.value
        }
      )
      Spacer(modifier = Modifier.height(16.dp))
      Box(modifier = Modifier.align(Alignment.CenterHorizontally)) {
        ButtonWithLoader(
          modifier = Modifier.offset(loginButtonOffset),
          isLoading.value,
          shouldEnableLoginButton.value,
          text = stringResource(id = R.string.login),
          onClick = loginViewModel::handleLoginClick
        )
      }
      Spacer(modifier = Modifier.height(8.dp))
      Box(modifier = Modifier.align(Alignment.CenterHorizontally)) {
        ButtonWithLoader(
          modifier = Modifier.offset(signupButtonOffset),
          isLoading = false,
          isEnabled = true,
          text = stringResource(id = R.string.signup),
          onClick = loginViewModel::handleSignUpClickInLoginScreen
        )
      }
    }
  }

  LaunchedEffect(viewState) {
    when (viewState) {
      is LoginViewState.EnterNavigation -> {
        reenterLoginScreenWithDelay()
      }
      is LoginViewState.SignUpNavigation -> {
        exitLoginScreenToSignupScreen(navHostController)
      }
      is LoginViewState.Empty -> {
        loginViewModel.notifyViewCreated()
      }
      is LoginViewState.InputValidation -> {
        with(viewState as LoginViewState.InputValidation) {
          if (inputUserName.value != userName) {
            inputUserName.value = userName
          }
          if (inputPassword.value != password) {
            inputPassword.value = password
          }
          shouldEnableLoginButton.value = isValid
        }
      }
      else -> {}
    }
  }

  DisposableEffect(Unit) {
    onDispose {
      loginViewModel.notifyViewRemoved()
    }
  }

}

private fun reenterLoginScreenWithDelay() {
  CoroutineScope(Dispatchers.IO).launch {
    delay(400)
    loginScreenTitleSlideOut.value = false
    delay(50)
    loginScreenUsernameSlideOut.value = false
    delay(50)
    loginScreenPasswordSlideOut.value = false
    delay(50)
    loginScreenLoginButtonSlideOut.value = false
    delay(50)
    loginScreenSignupButtonSlideOut.value = false
  }
}

private fun exitLoginScreenToSignupScreen(navHostController: NavHostController) {
  CoroutineScope(Dispatchers.Main).launch {
    exitLoginScreen()
    navHostController.navigate(AppNavigationRoutes.SIGNUP_SCREEN)
  }
}

private suspend fun exitLoginScreen() {
  loginScreenTitleSlideOut.value = true
  delay(50)
  loginScreenUsernameSlideOut.value = true
  delay(50)
  loginScreenPasswordSlideOut.value = true
  delay(50)
  loginScreenLoginButtonSlideOut.value = true
  delay(50)
  loginScreenSignupButtonSlideOut.value = true
}