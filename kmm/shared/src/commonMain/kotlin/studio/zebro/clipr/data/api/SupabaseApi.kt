package studio.zebro.clipr.data.api

import LoginUserResponseEntity
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.gotrue.gotrue
import io.github.jan.supabase.gotrue.providers.builtin.Email
import io.github.jan.supabase.plugins.SupabasePlugin
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

  suspend fun loginUser(userName: String, password: String): LoginUserResponseEntity {
    return supabaseClient.gotrue.loginWith(Email){
      email = userName
      this.password = password
    }.let {
      println(it)
      LoginUserResponseEntity(
        supabaseClient.gotrue.retrieveUserForCurrentSession().id,
        userName
      )
    }
  }

}