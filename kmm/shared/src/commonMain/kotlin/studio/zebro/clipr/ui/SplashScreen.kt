package studio.zebro.clipr.ui


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.unit.dp
import dev.icerock.moko.resources.compose.painterResource
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.query.RealmResults
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import studio.zebro.clipr.data.ClipboardDbEntity
import studio.zebro.clipr.sharedres
import studio.zebro.clipr.ui.theming.Colors

@Composable
fun SplashScreen() {
  Box(
    modifier = Modifier.fillMaxHeight()
      .fillMaxWidth()
      .background(Colors.primary800)
  ) {
    Column {
      Surface(
        modifier = Modifier.padding(16.dp),
        color = Colors.primary800
      ) {
        showTopBar()
      }
    }
  }
}

@Composable
fun showTopBar() {
  val animatableScale = remember { Animatable(0f) }
  val scope = rememberCoroutineScope()
  var isVisble by remember { mutableStateOf(false) }
  Row(
    modifier = Modifier
      .fillMaxWidth()
      .height(48.dp),
    horizontalArrangement = Arrangement.SpaceBetween,
    verticalAlignment = Alignment.CenterVertically
  ) {
    Card(
      shape = RoundedCornerShape(48.dp),
      colors = CardDefaults.cardColors(containerColor = Colors.white100),
      modifier = Modifier
        .animateContentSize(
          animationSpec = tween(
            durationMillis = 300,
          )
        )
    ) {
      Row(
        verticalAlignment = Alignment.CenterVertically
      ) {
        Image(
          painter = painterResource(sharedres.images.ic_clipr_main),
          contentDescription = null,
          modifier = Modifier
            .size(48.dp)
            .scale(animatableScale.value)
        )
        AnimatedVisibility(
          visible = isVisble,
        ) {
          Text(
            text = "ClipR",
            modifier = Modifier
              .background(Colors.white100)
              .padding(4.dp, 0.dp, 16.dp, 0.dp)
          )
        }
      }
    }

    Card(
      shape = RoundedCornerShape(48.dp),
      colors = CardDefaults.cardColors(containerColor = Colors.white100),
      modifier = Modifier
        .animateContentSize(
          animationSpec = tween(
            durationMillis = 300,
          )
        )
    ) {
      Image(
        painter = painterResource(sharedres.images.ic_settings),
        contentDescription = null,
        modifier = Modifier
          .size(42.dp)
          .scale(animatableScale.value)
      )
    }
  }

  scope.launch {
    animatableScale.animateTo(
      1f, spring(
        dampingRatio = Spring.DampingRatioMediumBouncy,
        stiffness = Spring.StiffnessLow
      )
    )
    delay(300)
    isVisble = true

    val config = RealmConfiguration.create(schema = setOf(ClipboardDbEntity::class))
    val realm: Realm = Realm.open(config)
    realm.writeBlocking {
      copyToRealm(
        ClipboardDbEntity().apply {
          copiedText = "Hello"
          copiedAt = Clock.System.now().epochSeconds.toString()
          imagePath = null
        }
      )
    }

    val items: RealmResults<ClipboardDbEntity> =
      realm.query(ClipboardDbEntity::class).find()

    items.forEach {
      println(it.copiedText)
    }

    realm.close()
  }
}