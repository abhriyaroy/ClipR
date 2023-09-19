package studio.zebro.clipr.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import parseException
import studio.zebro.clipr.data.ResourceState
import studio.zebro.clipr.data.api.SupabaseApi
import studio.zebro.clipr.data.db.DbManager
import studio.zebro.clipr.data.entity.signup.SignUpUserResponseEntity
import studio.zebro.clipr.data.exception.InvalidEmailException

interface UserRepository {
  fun isUserLoggedIn(): Boolean

  fun signUpUser(userName: String, password: String): Flow<ResourceState<SignUpUserResponseEntity>>

}

class UserRepositoryImpl(
  private val dbManager: DbManager,
  private val supabaseApi: SupabaseApi
) : UserRepository {

  override fun isUserLoggedIn(): Boolean {
    return dbManager.isUserLoggedIn()
  }

  override fun signUpUser(
    userName: String,
    password: String
  ): Flow<ResourceState<SignUpUserResponseEntity>> {
    return flow {
      emit(ResourceState.loading())
      val response = supabaseApi.signUpUser(userName, password)
      println(response)
      emit(ResourceState.success(response))
    }.catch {
      println(it.message)
      println(it)
      emit(ResourceState.error(parseException(it)))
    }
  }

}