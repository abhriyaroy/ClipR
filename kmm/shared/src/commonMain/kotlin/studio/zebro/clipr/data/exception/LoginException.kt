package studio.zebro.clipr.data.exception

const val INVALID_EMAIL_MESSAGE = "Unable to validate email address: invalid format"
data class InvalidEmailException(
  override val code : Int = 400,
  override val errorMessage : String = INVALID_EMAIL_MESSAGE
) : BaseException(code, errorMessage)

data class InvalidPasswordException(
  override val code : Int = 400,
  override val errorMessage : String
) : BaseException(code, errorMessage)