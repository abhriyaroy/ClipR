package studio.zebro.clipr.data.exception

open class BaseException constructor(
  open val code : Int = 0,
  open val errorMessage : String = "Something went wrong"
) : Exception()