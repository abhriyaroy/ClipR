//package studio.zebro.clipr.android.copieditemslist
//
//import androidx.lifecycle.SavedStateHandle
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import dagger.hilt.android.lifecycle.HiltViewModel
//import kotlinx.coroutines.flow.SharingStarted
//import kotlinx.coroutines.flow.combine
//import kotlinx.coroutines.flow.stateIn
//import kotlinx.coroutines.launch
//import kotlinx.datetime.Clock
//import kotlinx.datetime.LocalDateTime
//import kotlinx.datetime.TimeZone
//import kotlinx.datetime.toLocalDateTime
//import studio.zebro.clipr.domain.copieditems.CopiedItem
//import studio.zebro.clipr.domain.copieditems.CopiedItemsDataSource
//import studio.zebro.clipr.domain.copieditems.SearchCopiedNotesUseCase
//import javax.inject.Inject
//
//@HiltViewModel
//class CopiedItemsListViewModel @Inject constructor(
//  private val copiedItemsDataSource: CopiedItemsDataSource,
//  private val savedStateHandle: SavedStateHandle
//) : ViewModel() {
//
//  private val copiedItemsStateFlowKey = "copiedItems"
//  private val searchedTextStateFlowKey = "searchedText"
//  private val isSearchActiveStateFlowKey = "isSearchActive"
//
//  private val searchCopiedItemUseCase = SearchCopiedNotesUseCase()
//
//  private val copiedItems =
//    savedStateHandle.getStateFlow(copiedItemsStateFlowKey, emptyList<CopiedItem>())
//  private val searchText = savedStateHandle.getStateFlow(searchedTextStateFlowKey, "")
//  private val isSearchActive = savedStateHandle.getStateFlow(isSearchActiveStateFlowKey, false)
//
//  val state =
//    combine(copiedItems, searchText, isSearchActive) { copiedItems, searchText, isSearchActive ->
//      CopiedItemsListState(
//        searchCopiedItemUseCase.execute(copiedItems, searchText), searchText, isSearchActive
//      )
//    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), CopiedItemsListState())
//
//  init {
//    insertItem(
//      CopiedItem(
//        null,
//        false,
//        System.currentTimeMillis().toString(),
//        null,
//        Clock.System.now().toLocalDateTime(
//          TimeZone.currentSystemDefault()
//        )
//      )
//    )
//  }
//
//  fun loadAllCopiedItems() {
//    viewModelScope.launch {
//      savedStateHandle[copiedItemsStateFlowKey] = copiedItemsDataSource.getAllCopiedItems()
//    }
//  }
//
//  fun onSearchTextChanged(searchText: String) {
//    savedStateHandle[searchedTextStateFlowKey] = searchText
//  }
//
//  fun onToggleSearch() {
//    savedStateHandle[isSearchActiveStateFlowKey] = !isSearchActive.value
//    if (!isSearchActive.value) {
//      onSearchTextChanged("")
//    }
//  }
//
//  fun deleteCopiedItem(item: CopiedItem) {
//    viewModelScope.launch {
//      item.id?.run {
//        copiedItemsDataSource.deleteCopiedItemById(this)
//        loadAllCopiedItems()
//      }
//    }
//  }
//
//  fun insertItem(item: CopiedItem) {
//    viewModelScope.launch {
//      copiedItemsDataSource.insertCopiedItem(item)
//      loadAllCopiedItems()
//    }
//  }
//}