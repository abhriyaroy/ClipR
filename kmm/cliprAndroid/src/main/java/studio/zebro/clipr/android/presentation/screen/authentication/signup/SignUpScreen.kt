package studio.zebro.clipr.android.presentation.screen.authentication.signup

import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import kotlinx.coroutines.delay
import studio.zebro.clipr.android.R
import studio.zebro.clipr.android.presentation.viewmodel.SignUpViewModel
import studio.zebro.clipr.android.presentation.widgets.ButtonWithLoader
import studio.zebro.clipr.android.presentation.widgets.RoundedInputText
import studio.zebro.clipr.ui.theming.Colors


private var singUpScreenTitleSlideOut = mutableStateOf(false)
private var singUpScreenUsernameSlideOut = mutableStateOf(false)
private var singUpScreenPasswordSlideOut = mutableStateOf(false)
private var singUpScreenSignupButtonSlideOut = mutableStateOf(false)

@Composable
fun SignUpScreen(
  navHostController: NavHostController,
  signUpViewModel: SignUpViewModel
) {

  val viewState by signUpViewModel.viewState.collectAsState()

  val inputUserName = remember {
    mutableStateOf("")
  }
  val inputPassword = remember {
    mutableStateOf("")
  }
  val areInputCredentialsValid = remember { mutableStateOf(false) }
  val showLoader = remember { mutableStateOf(false) }
  val isPasswordHidden = remember { mutableStateOf(true) }

  val slideOutOffset = (5000).dp
  val originalOffset = 0.dp

  val titleTransition = updateTransition(singUpScreenTitleSlideOut.value, null)
  val userNameTransition = updateTransition(singUpScreenUsernameSlideOut.value, null)
  val passwordTransition = updateTransition(singUpScreenPasswordSlideOut.value, null)
  val signUpTransition =
    updateTransition(singUpScreenSignupButtonSlideOut.value, null)

  val titleTextFieldOffset by titleTransition.animateDp(label = "") { state ->
    if (state) originalOffset else slideOutOffset
  }

  val usernameTextFieldOffset by userNameTransition.animateDp(label = "") { state ->
    if (state) originalOffset else slideOutOffset
  }

  val passwordTextFieldOffset by passwordTransition.animateDp(label = "") { state ->
    if (state) originalOffset else slideOutOffset
  }

  val loginButtonOffset by signUpTransition.animateDp(label = "") { state ->
    if (state) originalOffset else slideOutOffset
  }

  BackHandler(true) {
    signUpViewModel.handleBackPress()
  }

  Column(
    modifier = Modifier
      .padding(8.dp)
      .fillMaxSize()
  ) {
    IconButton(
      onClick = {
        signUpViewModel.handleBackPress()
      }
    ) {
      Icon(
        modifier = Modifier.size(48.dp),
        imageVector = Icons.Default.ArrowBack,
        tint = Colors.white100,
        contentDescription = "Back"
      )
    }
    Column(
      modifier = Modifier
        .fillMaxSize()
        .padding(8.dp),
      verticalArrangement = Arrangement.Center,
    ) {
      Text(
        text = stringResource(id = R.string.signup),
        modifier = Modifier
          .fillMaxWidth()
          .offset(titleTextFieldOffset),
        style = MaterialTheme.typography.displayLarge,
        color = Colors.white100
      )
      Spacer(modifier = Modifier.height(8.dp))
      RoundedInputText(
        modifier = Modifier.offset(usernameTextFieldOffset),
        initialValue = inputUserName,
        hint = stringResource(id = R.string.username_hint),
        leadingImage = Icons.Default.Person,
        maxLines = 1,
        onTextChanged = {
          signUpViewModel.handleUserNameInput(it)
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
          signUpViewModel.handlePasswordInput(it)
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
          isLoading = showLoader.value,
          isEnabled = areInputCredentialsValid.value,
          text = stringResource(id = R.string.submit),
          onClick = signUpViewModel::handleSignUpClick
        )
      }
      Spacer(modifier = Modifier.height(24.dp))
    }
  }

  LaunchedEffect(viewState) {
    println("the new state $viewState")
    when (viewState) {
      is SignUpViewState.EnterNavigation -> {
        decorateSignUpScreen()
      }
      is SignUpViewState.ReturnNavigation -> {
        resetSignUpScreenState()
        navHostController.popBackStack()
      }
      is SignUpViewState.Empty -> {
        signUpViewModel.notifyViewCreated()
      }
      is SignUpViewState.InputValidation -> {
        with(viewState as SignUpViewState.InputValidation) {
          inputUserName.value = userName
          inputPassword.value = password
          areInputCredentialsValid.value = isValid
        }
      }
      is SignUpViewState.Loading -> {
        showLoader.value = true
      }
      is SignUpViewState.Success -> {
        showLoader.value = false
      }
      is SignUpViewState.Error -> {
        showLoader.value = false
        handleErrorState(viewState as SignUpViewState.Error)
      }
    }
  }

  DisposableEffect(Unit) {
    onDispose {
      signUpViewModel.notifyViewRemoved()
    }
  }
}

private suspend fun resetSignUpScreenState() {
  singUpScreenTitleSlideOut.value = false
  delay(50)
  singUpScreenUsernameSlideOut.value = false
  delay(50)
  singUpScreenPasswordSlideOut.value = false
  delay(50)
  singUpScreenSignupButtonSlideOut.value = false
}

private suspend fun decorateSignUpScreen() {
  singUpScreenTitleSlideOut.value = true
  delay(50)
  singUpScreenUsernameSlideOut.value = true
  delay(50)
  singUpScreenPasswordSlideOut.value = true
  delay(50)
  singUpScreenSignupButtonSlideOut.value = true
}

private fun handleErrorState(error: SignUpViewState.Error) {
  println("the error is $error")

}