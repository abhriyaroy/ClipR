package studio.zebro.clipr.data.repository

import LoginUserResponseEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import studio.zebro.clipr.data.exception.parseException
import studio.zebro.clipr.data.ResourceState
import studio.zebro.clipr.data.api.SupabaseApi
import studio.zebro.clipr.data.db.StorageManager

interface UserRepository {
  fun isUserLoggedIn(): Boolean

  fun signUpUserSession(
    userName: String,
    password: String
  ): Flow<ResourceState<LoginUserResponseEntity>>

  fun loginUserSession(
    userName: String,
    password: String
  ): Flow<ResourceState<LoginUserResponseEntity>>
}

class UserRepositoryImpl(
  private val storageManager: StorageManager,
  private val supabaseApi: SupabaseApi,
) : UserRepository {

  override fun isUserLoggedIn(): Boolean {
    return storageManager.isUserLoggedIn()
  }

  override fun signUpUserSession(
    userName: String,
    password: String
  ): Flow<ResourceState<LoginUserResponseEntity>> {
    return flow {
      emit(ResourceState.loading())
      val response = supabaseApi.signUpUser(userName, password)
      storageManager.saveUserLogin(response)
      emit(ResourceState.success(response))
    }.catch {
      println(it.message)
      println(it)
      emit(ResourceState.error(parseException(it)))
    }
  }

  override fun loginUserSession(
    userName: String,
    password: String
  ): Flow<ResourceState<LoginUserResponseEntity>> {
    return flow {
      emit(ResourceState.loading())
      val response = supabaseApi.loginUser(userName, password)
      storageManager.saveUserLogin(response)
      emit(ResourceState.success(response))
    }.catch {
      println(it.message)
      println(it)
      emit(ResourceState.error(parseException(it)))
    }
  }

}