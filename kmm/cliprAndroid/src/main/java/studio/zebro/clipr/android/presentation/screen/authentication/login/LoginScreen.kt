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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import kotlinx.coroutines.delay
import org.koin.androidx.compose.getViewModel
import studio.zebro.clipr.android.R
import studio.zebro.clipr.android.presentation.navigation.AppNavigationRoutes
import studio.zebro.clipr.android.presentation.viewmodel.LoginViewModel
import studio.zebro.clipr.android.presentation.widgets.ButtonWithLoader
import studio.zebro.clipr.android.presentation.widgets.RoundedInputText
import studio.zebro.clipr.data.exception.InvalidEmailException
import studio.zebro.clipr.data.exception.InvalidCredentialsException
import studio.zebro.clipr.ui.theming.Colors
import studio.zebro.clipr.ui.theming.Typography

@Composable
fun LoginScreen(navHostController: NavHostController) {

  val loginViewModel: LoginViewModel = getViewModel()
  val viewState by loginViewModel.viewState.collectAsState()

  val invalidEmailErrorMessage = stringResource(id = R.string.email_invalid_error)
  val wrongPasswordErrorMessage = stringResource(id = R.string.wrong_credentials_error)
  val genericErrorMessage = stringResource(id = R.string.generic_error)

  Box(modifier = Modifier.fillMaxSize()) {
    LoginScreenFields(
      navHostController = navHostController,
      loginViewModel = loginViewModel,
      viewState = viewState,
      modifier = Modifier.align(Alignment.CenterStart),
      invalidEmailErrorMessage,
      wrongPasswordErrorMessage,
      genericErrorMessage
    )
  }

  DisposableEffect(Unit) {
    onDispose {
      loginViewModel.notifyViewRemoved()
    }
  }

}

@Composable
private fun LoginScreenFields(
  navHostController: NavHostController,
  loginViewModel: LoginViewModel,
  viewState: LoginViewState,
  modifier: Modifier,
  invalidEmailErrorMessage: String,
  wrongPasswordErrorMessage: String,
  genericErrorMessage: String,
) {

  val inputUserName = remember {
    mutableStateOf("")
  }
  val inputPassword = remember {
    mutableStateOf("")
  }

  val errorMessage = remember { mutableStateOf("") }

  val shouldEnableLoginButton = remember { mutableStateOf(false) }
  val isLoading = remember { mutableStateOf(false) }

  val slideOutOffset = (-500).dp
  val originalOffset = 0.dp

  val loginScreenTitleSlideOut = remember { mutableStateOf(false) }
  val loginScreenUsernameSlideOut = remember { mutableStateOf(false) }
  val loginScreenPasswordSlideOut = remember { mutableStateOf(false) }
  val loginScreenLoginButtonSlideOut = remember { mutableStateOf(false) }
  val loginScreenSignupButtonSlideOut = remember { mutableStateOf(false) }

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

  Column(
    modifier = modifier
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
      hint = stringResource(id = R.string.email_hint),
      leadingImage = Icons.Default.Person,
      maxLines = 1,
      onTextChanged = {
        loginViewModel.handleUserNameInput(it)
      },
    )
    Spacer(modifier = Modifier.height(8.dp))
    PasswordField(loginViewModel, passwordTextFieldOffset, inputPassword)
    if (errorMessage.value.isNotEmpty()) {
      Spacer(modifier = Modifier.height(8.dp))
      Text(
        modifier = Modifier.padding(start = 8.dp, end = 8.dp),
        text = errorMessage.value,
        style = Typography.ClipRTypography.labelSmall,
        color = Colors.error800
      )
    }
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

  LaunchedEffect(viewState) {
    errorMessage.value = ""
    when (viewState) {
      is LoginViewState.EnterNavigation -> {
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

      is LoginViewState.SignUpNavigation -> {
        loginScreenTitleSlideOut.value = true
        delay(50)
        loginScreenUsernameSlideOut.value = true
        delay(50)
        loginScreenPasswordSlideOut.value = true
        delay(50)
        loginScreenLoginButtonSlideOut.value = true
        delay(50)
        loginScreenSignupButtonSlideOut.value = true
        navHostController.navigate(AppNavigationRoutes.SIGNUP_SCREEN)
      }

      is LoginViewState.Empty -> {
        loginViewModel.notifyViewCreated()
      }

      is LoginViewState.InputValidation -> {
        if (inputUserName.value != viewState.userName) {
          inputUserName.value = viewState.userName
        }
        if (inputPassword.value != viewState.password) {
          inputPassword.value = viewState.password
        }
        shouldEnableLoginButton.value = viewState.isValid
      }

      is LoginViewState.Loading -> {
        isLoading.value = true
      }
      is LoginViewState.LoginSuccess -> {
        isLoading.value = false
        navHostController.navigate(AppNavigationRoutes.HOME_SCREEN)
      }

      is LoginViewState.LoginError -> {
        isLoading.value = false
        when (viewState.error) {
          is InvalidEmailException -> {
            errorMessage.value = invalidEmailErrorMessage
          }

          is InvalidCredentialsException -> {
            errorMessage.value = wrongPasswordErrorMessage
          }

          else -> {
            errorMessage.value = genericErrorMessage
          }
        }
        println("the error is ${viewState.error}")
      }

      else -> {}
    }
  }
}

@Composable
private fun PasswordField(
  loginViewModel: LoginViewModel,
  passwordTextFieldOffset: Dp,
  inputPassword: MutableState<String>
) {
  val isPasswordHidden = remember { mutableStateOf(true) }

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
}