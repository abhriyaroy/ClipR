package studio.zebro.clipr.data.repository

import LoginUserResponseEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
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

  fun hasRequestedPermissionsBefore(permissionsList: List<String>): Boolean

  fun saveDeniedPermissions(permissionsList: List<String>)
}

class UserRepositoryImpl(
  private val storageManager: StorageManager,
  private val supabaseApi: SupabaseApi,
) : UserRepository {

  val domainNameToAppendToUsername = "@clipr.zebro"

  override fun isUserLoggedIn(): Boolean {
    return storageManager.isUserLoggedIn()
//    return false
  }

  override fun signUpUserSession(
    userName: String,
    password: String
  ): Flow<ResourceState<LoginUserResponseEntity>> {
    return flow {
      emit(ResourceState.loading())
      val response = supabaseApi.signUpUser(userName + domainNameToAppendToUsername, password)
      storageManager.saveUserLogin(response)
      emit(ResourceState.success(response))
    }.catch {
      println(it.message)
      println(it)
      emit(ResourceState.error(parseException(it)))
    }.flowOn(Dispatchers.IO)
  }

  override fun loginUserSession(
    userName: String,
    password: String
  ): Flow<ResourceState<LoginUserResponseEntity>> {
    return flow {
      emit(ResourceState.loading())
      val response = supabaseApi.loginUser(userName + domainNameToAppendToUsername, password)
      storageManager.saveUserLogin(response)
      emit(ResourceState.success(response))
    }.catch {
      println(it.message)
      println(it)
      emit(ResourceState.error(parseException(it)))
    }.flowOn(Dispatchers.IO)
  }

  override fun saveDeniedPermissions(permissionsList: List<String>) {
    storageManager.saveDeniedUserPermissions(permissionsList)
  }

  override fun hasRequestedPermissionsBefore(permissionsList: List<String>): Boolean {
    return storageManager.getDeniedUserPermissions().any { permissionsList.contains(it) }
  }

}