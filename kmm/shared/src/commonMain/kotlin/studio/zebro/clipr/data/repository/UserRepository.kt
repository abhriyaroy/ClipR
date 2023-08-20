package studio.zebro.clipr.data.repository

import studio.zebro.clipr.data.db.DbManager

interface UserRepository {
  fun isUserLoggedIn(): Boolean

}

class UserRepositoryImpl(
  private val dbManager: DbManager
) : UserRepository {

  override fun isUserLoggedIn(): Boolean {
    return dbManager.isUserLoggedIn()
  }

}