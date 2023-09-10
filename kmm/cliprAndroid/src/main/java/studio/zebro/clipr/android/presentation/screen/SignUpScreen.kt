package studio.zebro.clipr.android.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import studio.zebro.clipr.android.presentation.viewmodel.LoginViewModel
import studio.zebro.clipr.ui.theming.Colors

@Composable
fun SignUpScreen(navHostController: NavHostController, loginViewModel: LoginViewModel) {

  println("the hash is - signup -  ${loginViewModel.hashCode()}")

  loginViewModel.updateNavigatedToSignUpScreen()

  Column(modifier = Modifier.fillMaxSize()) {
    IconButton(
      onClick = {
      println("go back")
        navHostController.popBackStack() }
    ) {
      Icon(
        modifier = Modifier.size(48.dp),
        imageVector = Icons.Default.ArrowBack,
        tint = Colors.white100,
        contentDescription = "Back"
      )
    }
  }
}