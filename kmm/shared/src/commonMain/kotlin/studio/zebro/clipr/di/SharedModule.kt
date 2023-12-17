package studio.zebro.clipr.di

import org.koin.dsl.module
import studio.zebro.clipr.data.api.NetworkClient
import studio.zebro.clipr.data.api.SupabaseApi
import studio.zebro.clipr.data.db.StorageManager
import studio.zebro.clipr.data.db.StorageManagerImpl
import studio.zebro.clipr.data.repository.UserRepository
import studio.zebro.clipr.data.repository.UserRepositoryImpl

val sharedModule = module {
  single<StorageManager> { StorageManagerImpl() }
  single<UserRepository> { UserRepositoryImpl(get(), get()) }

  single { NetworkClient().createHttpClient() }
  single { NetworkClient().createClipRSupabaseClient() }

  single { SupabaseApi(get()) }
}