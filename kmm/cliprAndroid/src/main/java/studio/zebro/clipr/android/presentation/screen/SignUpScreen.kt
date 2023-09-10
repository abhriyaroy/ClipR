package studio.zebro.clipr.android.presentation.screen

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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
fun SignUpScreen(
  navHostController: NavHostController,
  loginViewModel: LoginViewModel,
) {

  val slideOutOffset = (500).dp
  val originalOffset = 0.dp

  val titleTransition = updateTransition(loginViewModel.singUpScreentitleSlideOut.value, null)
  val userNameTransition = updateTransition(loginViewModel.singUpScreenUsernameSlideOut.value, null)
  val passwordTransition = updateTransition(loginViewModel.singUpScreenPasswordSlideOut.value, null)
  val signUpTransition =
    updateTransition(loginViewModel.singUpScreenSignupButtonSlideOut.value, null)

  val titleTextFieldOffset by titleTransition.animateDp(label = "") { state ->
    println("sate is $state")
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
    loginViewModel.handleBackClickFromSignUp()
    navHostController.popBackStack()
  }

  loginViewModel.updateNavigatedToSignUpScreen()

  Column(
    modifier = Modifier
      .padding(8.dp)
      .fillMaxSize()
  ) {
    IconButton(
      onClick = {
        loginViewModel.handleBackClickFromSignUp()
        navHostController.popBackStack()
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
          text = stringResource(id = R.string.signup),
          onClick = loginViewModel::handleSignUpClickInSignUpScreen
        )
      }
      Spacer(modifier = Modifier.height(24.dp))
    }
  }
}