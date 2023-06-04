//package studio.zebro.clipr.android.copieditemslist
//
//import androidx.compose.animation.AnimatedVisibility
//import androidx.compose.animation.fadeIn
//import androidx.compose.animation.fadeOut
//import androidx.compose.foundation.ExperimentalFoundationApi
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
//import androidx.compose.material.FloatingActionButton
//import androidx.compose.material.Icon
//import androidx.compose.material.Scaffold
//import androidx.compose.material.Text
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.ShoppingCart
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.runtime.collectAsState
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.hilt.navigation.compose.hiltViewModel
//
//@OptIn(ExperimentalFoundationApi::class)
//@Composable
//fun CopiedItemsListScreen(
//  viewModel: CopiedItemsListViewModel = hiltViewModel()
//) {
//
//  val state = viewModel.state.collectAsState().value
//
//  LaunchedEffect(key1 = true) {
//    viewModel.loadAllCopiedItems()
//  }
//
//  Scaffold(
//    floatingActionButton = {
//      FloatingActionButton(
//        onClick = {},
//        backgroundColor = Color.Black
//      ) {
//        Icon(
//          imageVector = Icons.Default.ShoppingCart,
//          contentDescription = "Copy from clipboard",
//          tint = Color.White
//        )
//      }
//    }
//  ) { paddingValues ->
//    Column(
//      modifier = Modifier
//        .fillMaxSize()
//        .padding(paddingValues),
//      horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//      Box(
//        modifier = Modifier.fillMaxWidth(),
//        contentAlignment = Alignment.Center
//      ) {
//        HideableSearchTextField(
//          text = state.searchText,
//          isSearchActive = state.isSearchActive,
//          onTextChangeListener = viewModel::onSearchTextChanged,
//          onSearchClickListener = viewModel::onToggleSearch,
//          onCloseClickListener = viewModel::onToggleSearch,
//          modifier = Modifier
//            .fillMaxWidth()
//            .height(58.dp)
//        )
//
//        this@Column.AnimatedVisibility(
//          visible = !state.isSearchActive,
//          enter = fadeIn(),
//          exit = fadeOut()
//        ) {
//
//          Text(
//            text = "History",
//            fontWeight = FontWeight.Bold,
//            fontSize = 30.sp
//          )
//        }
//      }
//
//      LazyColumn(
//        modifier = Modifier.weight(1f)
//      ) {
//        items(items = state.copiedItemsList, key = {
//          it.id!!
//        }) { copiedItem ->
//          CopiedItemView(
//            copiedItem = copiedItem,
//            onCopiedItemClick = {},
//            onDeleteItemClick = viewModel::deleteCopiedItem,
//            modifier = Modifier
//              .fillMaxWidth()
//              .padding(16.dp)
//              .animateItemPlacement()
//          )
//        }
//      }
//    }
//  }
//
//}