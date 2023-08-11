package studio.zebro.clipr.data.db

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

class ClipboardDbEntity : RealmObject {
  @PrimaryKey
  var id: ObjectId = ObjectId()
  var copiedAt: String = ""
  var copiedText: String = ""
  var imagePath: String? = null
}