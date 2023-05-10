package studio.zebro.clipr.domain.copieditems

import kotlinx.datetime.LocalDateTime

data class CopiedItem(
  val id: Long?,
  val hasAttachment: Boolean,
  val contentText: String?,
  val attachmentLocation: String?,
  val createdAt: LocalDateTime
)
