package studio.zebro.clipr.data.local

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import studio.zebro.clipr.CopiedItemsDatabase

actual class DatabaseDriverFactory(private val context: Context) {
  actual fun createDriver() : SqlDriver {
    return AndroidSqliteDriver(CopiedItemsDatabase.Schema, context, "copieditems.db")
  }
}