package studio.zebro.clipr.data.copieditems

import studio.zebro.clipr.CopiedItemsDatabase
import studio.zebro.clipr.commonUtils.toLong
import studio.zebro.clipr.domain.copieditems.CopiedItem
import studio.zebro.clipr.domain.copieditems.CopiedItemsDataSource
import studio.zebro.clipr.domain.time.DateTimeUtil

class SqlDelightCopiedItemsDataSource(copiedItemsDatabase: CopiedItemsDatabase) :
  CopiedItemsDataSource {

  val queries = copiedItemsDatabase.clipboardQueries

  override suspend fun insertCopiedItem(copiedItem: CopiedItem) {
    queries.insertCopiedItemData(
      copiedItem.id,
      copiedItem.hasAttachment.toLong(),
      copiedItem.contentText,
      copiedItem.attachmentLocation,
      DateTimeUtil.toEpochMillis(copiedItem.createdAt)
    )
  }

  override suspend fun getCopiedItemById(id: Long): CopiedItem? {
    return queries.getCopiedItemById(id)
      .executeAsOneOrNull()
      ?.toCopiedItem()
  }

  override suspend fun getAllCopiedItems(): List<CopiedItem> {
    return queries.getAllCopiedItemData()
      .executeAsList()
      .map {
        it.toCopiedItem()
      }
  }

  override suspend fun deleteCopiedItemById(id: Long) {
    return queries.deleteCopiedItemById(id)
  }
}