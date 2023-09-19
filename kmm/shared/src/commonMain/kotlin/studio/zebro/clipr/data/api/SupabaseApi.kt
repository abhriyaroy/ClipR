package studio.zebro.clipr.data.api

import SignUpUserRequestEntity
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.gotrue.gotrue
import io.github.jan.supabase.gotrue.providers.builtin.Email
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.content.*
import io.ktor.http.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonNull.content
import studio.zebro.clipr.data.entity.signup.SignUpUserResponseEntity

class SupabaseApi(private val supabaseClient: SupabaseClient) {

  suspend fun signUpUser(userName: String, password: String): SignUpUserResponseEntity {
    return supabaseClient.gotrue.signUpWith(Email){
      email = userName
      this.password = password
    }.let {
      println(it)
      SignUpUserResponseEntity(
        it!!.id,
        it.email
      )
    }
  }

}