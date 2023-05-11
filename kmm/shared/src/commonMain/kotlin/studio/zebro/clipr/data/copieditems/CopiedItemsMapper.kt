package studio.zebro.clipr.data.copieditems

import database.CopiedItemEntity
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import studio.zebro.clipr.commonUtils.toBoolean
import studio.zebro.clipr.domain.copieditems.CopiedItem

fun CopiedItemEntity.toCopiedItem() : CopiedItem =
  CopiedItem(
    this.id,
    this.hasAttachment.toBoolean(),
    this.contentText,
    this.attachmentLocation,
    Instant.fromEpochMilliseconds(this.createdAt).toLocalDateTime(TimeZone.currentSystemDefault()),
  )