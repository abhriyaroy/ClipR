package studio.zebro.clipr.data.exception

const val INVALID_EMAIL_MESSAGE = "Unable to validate email address: invalid format"
data class InvalidEmailException(
  val code : Int = 400,
  val errorMessage : String = INVALID_EMAIL_MESSAGE
) : Exception()

data class InvalidPasswordException(
  val code : Int = 400,
  val errorMessage : String
) : Exception()