package studio.zebro.clipr.android.presentation.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import studio.zebro.clipr.ui.theming.Colors
import studio.zebro.clipr.ui.theming.Typography

@Composable
fun RoundedInputText(
  modifier: Modifier = Modifier,
  initialValue : MutableState<String> = mutableStateOf(""),
  hint: String = "",
  onTextChanged: (String) -> Unit,
  shape: Shape = RoundedCornerShape(48.dp),
  backgroundColor: Color = Colors.primary100,
  textColor: Color = Colors.white100,
  textStyle: TextStyle = Typography.ClipRTypography.headlineLarge,
  cursorColor: Color = Colors.white100,
  leadingImage: ImageVector? = null,
  imageTint: Color = Colors.white100,
  maxLines: Int = 5,
  isPasswordField : Boolean = false
) {

//  val text = remember {
//   mutableStateOf(initialValue)
//  }

  Box(
    modifier = modifier
      .background(backgroundColor, shape = shape)
  ) {
    BasicTextField(
      value = initialValue.value,
      onValueChange = {
        initialValue.value = it
        onTextChanged(it)
      },
      textStyle = textStyle,
      keyboardOptions = KeyboardOptions.Default.copy(
        keyboardType = KeyboardType.Text
      ),
      visualTransformation = if (!isPasswordField) VisualTransformation.None else PasswordVisualTransformation(),
      cursorBrush = SolidColor(cursorColor),
      decorationBox = { innerTextField ->
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
          verticalAlignment = Alignment.CenterVertically
        ) {
          leadingImage?.let {
            Icon(
              imageVector = it,
              contentDescription = null,
              modifier = Modifier.size(24.dp),
              tint = imageTint
            )
          }
          Spacer(modifier = Modifier.width(8.dp))
          Box(
            modifier = Modifier.weight(1f),
            contentAlignment = Alignment.BottomStart
          ) {
            if (initialValue.value.isEmpty()) {
              Text(
                text = hint,
                color = textColor.copy(alpha = 0.5f), // Set hint color here
              )
            }
            innerTextField()
          }
        }
      },
      maxLines = maxLines,
    )
  }
}