package studio.zebro.clipr.data.db

import LoginUserResponseEntity
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import studio.zebro.kcrypt.getKCrypt
import kotlin.io.encoding.ExperimentalEncodingApi

interface StorageManager {

  fun saveUserLogin(loginUserResponseEntity: LoginUserResponseEntity)
  fun isUserLoggedIn(): Boolean
}

@OptIn(ExperimentalEncodingApi::class)
class StorageManagerImpl : StorageManager {

  private val dbVersion = 1L

  private var realm: Realm? = null

  private val isUserLoggedInKey  = "isUserLoggedIn"

  init {
    CoroutineScope(Dispatchers.IO).launch {
      if (getKCrypt().getEncryptionKey() != null) {
        realm = Realm.open(getConfig())
      }
    }
  }


  override fun saveUserLogin(loginUserResponseEntity: LoginUserResponseEntity) {
    getKCrypt().saveEncryptionKey(loginUserResponseEntity.id, false)
    getKCrypt().saveBoolean(isUserLoggedInKey, true)
    realm = Realm.open(getConfig())

    realm?.writeBlocking {
      copyToRealm(
        UserDbEntity().apply {
          userId = loginUserResponseEntity.id
        }
      )
    }
  }

  override fun isUserLoggedIn(): Boolean {
    return getKCrypt().getBoolean(isUserLoggedInKey) ?: false
  }

  private fun getConfig() = RealmConfiguration
    .Builder(
      schema = setOf(
        UserDbEntity::class,
        ClipboardDbEntity::class
      )
    )
    .schemaVersion(dbVersion)
    .encryptionKey(getKCrypt().getEncryptionKey(64)!!.apply {
      println("the key size is ${this.size}")
    })
    .name("clipr.realm")
    .build()


}