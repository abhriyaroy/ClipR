import studio.zebro.clipr.data.exception.BaseException
import studio.zebro.clipr.data.exception.INVALID_EMAIL_MESSAGE
import studio.zebro.clipr.data.exception.InvalidEmailException

fun parseException(throwable: Throwable): BaseException {
  if (throwable.message?.contains(INVALID_EMAIL_MESSAGE) == true) {
    return InvalidEmailException()
  }
  return BaseException()
}