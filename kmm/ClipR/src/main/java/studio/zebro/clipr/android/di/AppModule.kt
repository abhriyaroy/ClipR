package studio.zebro.clipr.android.di

import android.app.Application
import app.cash.sqldelight.db.SqlDriver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import studio.zebro.clipr.CopiedItemsDatabase
import studio.zebro.clipr.data.copieditems.SqlDelightCopiedItemsDataSource
import studio.zebro.clipr.data.local.DatabaseDriverFactory
import studio.zebro.clipr.domain.copieditems.CopiedItemsDataSource
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

  @Provides
  @Singleton
  fun providesSqlDriver(app: Application): SqlDriver = DatabaseDriverFactory(app).createDriver()

  @Provides
  @Singleton
  fun providesCopiedItemsDataSource(driver: SqlDriver): CopiedItemsDataSource =
    SqlDelightCopiedItemsDataSource(CopiedItemsDatabase(driver))
}