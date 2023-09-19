import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SignUpUserRequestEntity(
  @SerialName("email")
  val userName: String,
  @SerialName("password")
  val password: String
)