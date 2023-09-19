package studio.zebro.clipr.data.api

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.SupabaseClientBuilder
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.GoTrue
import kotlinx.serialization.json.Json
import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import studio.zebro.clipr.data.DEBUG_BASE_URL
import studio.zebro.clipr.data.DEBUG_TOKEN
import studio.zebro.clipr.httpClient

class NetworkClient {
  fun createHttpClient(): HttpClient = httpClient {

    install(ContentNegotiation) {
      json(
        Json {
          prettyPrint = true
          isLenient = true
          ignoreUnknownKeys = true
        }
      )
    }

    install(DefaultRequest) {
      headers.append("Content-Type", "application/json")
    }


    install(Logging) {
      logger = Logger.DEFAULT
      level = LogLevel.INFO
    }
  }

  fun createClipRSupabaseClient() =
    createSupabaseClient(
      DEBUG_BASE_URL,
      DEBUG_TOKEN
    ) {
      install(GoTrue)
    }
}