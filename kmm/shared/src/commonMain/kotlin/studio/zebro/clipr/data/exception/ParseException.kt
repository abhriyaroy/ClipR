package studio.zebro.clipr.data.exception

fun parseException(throwable: Throwable): BaseException {
  if (throwable.message?.contains(INVALID_EMAIL_MESSAGE) == true) {
    return InvalidEmailException()
  } else if (throwable.message?.contains(INVALID_CREDENTIALS_MESSAGE) == true) {
    return InvalidCredentialsException()
  }
  return BaseException()
}