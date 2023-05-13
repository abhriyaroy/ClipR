package studio.zebro.clipr.domain.copieditems

import studio.zebro.clipr.domain.time.DateTimeUtil

class SearchCopiedNotesUseCase {

  fun execute(notes: List<CopiedItem>, query: String): List<CopiedItem> {
    if (query.isBlank()) {
      return notes
    }
    return notes.filter {
      it.contentText?.trim()?.lowercase()?.contains(query.lowercase()) == true
    }.sortedBy {
      DateTimeUtil.toEpochMillis(it.createdAt)
    }
  }

}