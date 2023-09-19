import studio.zebro.clipr.data.exception.INVALID_EMAIL_MESSAGE
import studio.zebro.clipr.data.exception.InvalidEmailException

fun parseException(throwable: Throwable): Exception {
  if (throwable.message?.contains(INVALID_EMAIL_MESSAGE) == true) {
    return InvalidEmailException()
  }
  return Exception()
}