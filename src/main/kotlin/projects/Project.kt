package projects

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.OffsetDateTime

data class Project(
    val id: Int,
    val name: String,
    val public: Boolean,
    val open: Boolean,
    val created: OffsetDateTime,

    val description: String?,

    @JsonProperty("reference_language")
    val referenceLanguage: String?,

    val terms: Int?
)