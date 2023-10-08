package studio.zebro.clipr.android.presentation.navigation


object AppNavigationRoutes {
  const val LOGIN_SCREEN = "login"
  const val SIGNUP_SCREEN = "signup"
  const val HOME_SCREEN = "homescreen"
  const val EMPTY = ""
}

object TransitionKeys {
  const val LOGIN_TO_SINGUP_TRANSITION = "login_to_signup_transition"
}

//@Composable
//fun rememberSavedStateHandle(): SavedStateHandle {
//  val savedStateRegistry = LocalSavedStateRegistry.current
//  val owner = LocalView.current.findViewTreeSavedStateRegistryOwner()
//  return remember(savedStateRegistry, owner) {
//    SavedStateHandleController.create(owner, savedStateRegistry).savedStateHandle
//  }
//}