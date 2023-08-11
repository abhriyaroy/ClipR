package studio.zebro.clipr.data.db

import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import studio.zebro.clipr.platformSpecifics.generateSecureRandomKey

class DbManager {

  private val dbVersion = 1L

  private val realm: Realm

  init {
    val config = RealmConfiguration
      .Builder(
        schema = setOf(
          UserDbEntity::class,
          ClipboardDbEntity::class
        )
      )
      .schemaVersion(dbVersion)
      .encryptionKey(generateSecureRandomKey())
      .build()

    realm = Realm.open(config)


//    realm.writeBlocking {
//      copyToRealm(
//        ClipboardDbEntity().apply {
//          copiedText = "Hello"
//          copiedAt = Clock.System.now().epochSeconds.toString()
//          imagePath = null
//        }
//      )
//    }
//
//    val items: RealmResults<ClipboardDbEntity> =
//      realm.query(ClipboardDbEntity::class).find()
//
//    items.forEach {
//      println(it.copiedText)
//    }
//
//    realm.close()
  }

  fun isUserLoggedIn(): Boolean {
    return realm.query(UserDbEntity::class).find().isNotEmpty()
  }


}