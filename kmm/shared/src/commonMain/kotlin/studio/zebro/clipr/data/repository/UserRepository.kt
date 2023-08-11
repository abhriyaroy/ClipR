package studio.zebro.clipr.data.repository

import studio.zebro.clipr.data.db.DbManager

class UserRepository(
  private val dbManager: DbManager
) {

  fun isUserLoggedIn(): Boolean {
    return dbManager.isUserLoggedIn()
  }

}