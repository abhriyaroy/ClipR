package studio.zebro.clipr.android.presentation.screen.home.states

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import studio.zebro.clipr.android.R
import studio.zebro.clipr.android.presentation.viewmodel.LandingViewModel
import studio.zebro.clipr.ui.theming.Colors

@Composable
fun HomeScreenStateEmpty(landingViewModel: LandingViewModel) {
  val emptyIcon = painterResource(id = R.drawable.ic_empty_box)

  Box(
    modifier = Modifier
      .fillMaxSize()
      .padding(16.dp)
  ) {
    Column(
      modifier = Modifier
        .align(Alignment.Center)
        .fillMaxSize(),
      verticalArrangement = Arrangement.Center,
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      Icon(
        modifier = Modifier
          .size(72.dp)
          .fillMaxWidth(),
        painter = emptyIcon,
        contentDescription = "No history found",
        tint = Color.Unspecified
      )
      Text(
        text = stringResource(id = R.string.home_empty_message),
        modifier = Modifier
          .padding(0.dp, 4.dp)
          .fillMaxWidth(),
        textAlign = TextAlign.Center
      )
    }

    FloatingActionButton(
      onClick = {
        landingViewModel.handlePasteButtonClick()
      },
      modifier = Modifier
        .align(Alignment.BottomEnd),
      containerColor = Colors.accent800,
    ) {
      Icon(
        painterResource(id = R.drawable.ic_copy),
        modifier = Modifier.size(24.dp),
        contentDescription = "Paste from clipboard",
        tint = Colors.white100
      )
    }
  }
}

