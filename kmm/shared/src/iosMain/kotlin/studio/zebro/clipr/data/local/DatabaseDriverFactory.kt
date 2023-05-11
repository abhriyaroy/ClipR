package studio.zebro.clipr.data.local

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import studio.zebro.clipr.CopiedItemsDatabase

actual class DatabaseDriverFactory {
  actual fun createDriver() : SqlDriver {
    return NativeSqliteDriver(CopiedItemsDatabase.Schema, "copieditems.db")
  }
}