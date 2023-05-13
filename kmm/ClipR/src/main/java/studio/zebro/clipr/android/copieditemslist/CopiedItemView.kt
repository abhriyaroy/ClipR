package studio.zebro.clipr.android.copieditemslist

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import studio.zebro.clipr.domain.copieditems.CopiedItem
import studio.zebro.clipr.domain.time.DateTimeUtil

@Composable
fun CopiedItemView(
  copiedItem: CopiedItem,
  onCopiedItemClick: (CopiedItem) -> Unit,
  onDeleteItemClick: (CopiedItem) -> Unit,
  modifier: Modifier = Modifier
) {
  val formattedDate = remember(copiedItem) {
    DateTimeUtil.formatClipboardItemDate(copiedItem.createdAt)
  }

  Column(
    modifier = modifier
      .clip(RoundedCornerShape(4.dp))
      .background(Color.Cyan)
      .clickable {
        onCopiedItemClick(copiedItem)
      }
      .padding(16.dp)
  ) {
    Row(
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically,
      modifier = Modifier.fillMaxWidth()
    ) {
      Text(
        text = copiedItem.contentText ?: "  ",
        fontWeight = FontWeight.SemiBold,
        fontSize = 12.sp,
        color = Color.Black
      )

      Icon(
        imageVector = Icons.Default.Close,
        contentDescription = "Delete copied item",
        modifier = modifier.clickable(MutableInteractionSource(), null) {
          onDeleteItemClick(copiedItem)
        }
      )
    }

    Spacer(modifier = modifier.height(8.dp))

    Text(
      text = formattedDate,
      color = Color.DarkGray,
      modifier = modifier.align(Alignment.End)
    )
  }

}