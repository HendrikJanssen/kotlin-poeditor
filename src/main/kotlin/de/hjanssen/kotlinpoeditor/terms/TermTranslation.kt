package de.hjanssen.kotlinpoeditor.terms

import java.time.OffsetDateTime

data class TermTranslation(
    val content: String,
    val fuzzy: Boolean,
    val updated: OffsetDateTime?
)
