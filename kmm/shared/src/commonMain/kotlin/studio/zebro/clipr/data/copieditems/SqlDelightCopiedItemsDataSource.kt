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
    TODO("Not yet implemented")
  }

  override suspend fun getAllCopiedItems(): List<CopiedItem> {
    TODO("Not yet implemented")
  }

  override suspend fun deleteCopiedItemById(id: Long) {
    TODO("Not yet implemented")
  }
}