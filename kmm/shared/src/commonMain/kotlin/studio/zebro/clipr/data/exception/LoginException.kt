package studio.zebro.clipr.data.exception

import studio.zebro.clipr.utils.BAD_REQUEST_CODE

const val INVALID_EMAIL_MESSAGE = "Unable to validate email address: invalid format"
const val INVALID_CREDENTIALS_MESSAGE = "Invalid login credentials"

data class InvalidEmailException(
  override val code : Int = BAD_REQUEST_CODE,
  override val errorMessage : String = INVALID_EMAIL_MESSAGE
) : BaseException(code, errorMessage)

data class InvalidCredentialsException(
  override val code : Int = BAD_REQUEST_CODE,
  override val errorMessage : String = INVALID_CREDENTIALS_MESSAGE
) : BaseException(code, errorMessage)