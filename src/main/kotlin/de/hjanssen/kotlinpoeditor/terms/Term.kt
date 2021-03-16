package de.hjanssen.kotlinpoeditor.terms

import java.time.OffsetDateTime

data class Term(

    val term: String,

    val context: String,
    val plural: String,
    val reference: String,
    val comment: String,

    val tags: List<String>,

    val created: OffsetDateTime,
    val updated: OffsetDateTime?,

    val translation: TermTranslation?
)

