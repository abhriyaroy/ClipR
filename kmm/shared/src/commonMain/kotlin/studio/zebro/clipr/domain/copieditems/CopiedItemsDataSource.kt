package studio.zebro.clipr.domain.copieditems

interface CopiedItemsDataSource {
  suspend fun insertCopiedItem(copiedItem: CopiedItem)
  suspend fun getCopiedItemById(id: Long): CopiedItem?
  suspend fun getAllCopiedItems(): List<CopiedItem>
  suspend fun deleteCopiedItemById(id: Long)
}