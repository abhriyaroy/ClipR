package studio.zebro.clipr.android.presentation.navigation


object AppNavigationRoutes {
  const val LOGIN_SCREEN_NAME = "login"
  const val SIGNUP_SCREEN_NAME = "signup"
  const val EMPTY = ""
}

//@Composable
//fun rememberSavedStateHandle(): SavedStateHandle {
//  val savedStateRegistry = LocalSavedStateRegistry.current
//  val owner = LocalView.current.findViewTreeSavedStateRegistryOwner()
//  return remember(savedStateRegistry, owner) {
//    SavedStateHandleController.create(owner, savedStateRegistry).savedStateHandle
//  }
//}