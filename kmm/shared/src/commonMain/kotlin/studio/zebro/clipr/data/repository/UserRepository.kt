package studio.zebro.clipr.data.repository

import kotlinx.coroutines.flow.Flow
import studio.zebro.clipr.data.ResourceState
import studio.zebro.clipr.data.db.DbManager
import studio.zebro.clipr.data.entity.signup.SignUpUserResponseEntity

interface UserRepository {
  fun isUserLoggedIn(): Boolean

//  fun signUpUser(userName: String, password: String): Flow<ResourceState<SignUpUserResponseEntity>>

}

class UserRepositoryImpl(
  private val dbManager: DbManager
) : UserRepository {

  override fun isUserLoggedIn(): Boolean {
    return dbManager.isUserLoggedIn()
  }

}