package studio.zebro.clipr.data.db

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.BsonObjectId
import org.mongodb.kbson.ObjectId

class UserDbEntity : RealmObject {
  @PrimaryKey
  var id: ObjectId = BsonObjectId()
}