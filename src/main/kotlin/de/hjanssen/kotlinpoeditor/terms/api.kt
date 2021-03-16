package de.hjanssen.kotlinpoeditor.terms

import de.hjanssen.kotlinpoeditor.api.POEditorApi
import de.hjanssen.kotlinpoeditor.api.POEditorResponse

data class ListTermsResponse(
    val terms: List<Term>
)

suspend inline fun listTerms(api: POEditorApi, projectId: Int, language: String?): List<Term> {

    var parameters = listOf("id" to "$projectId")

    language?.let {
        parameters = parameters + listOf("language" to language)
    }

    val response =
        api.requestApi<POEditorResponse<ListTermsResponse>>("/v2/terms/list", parameters)

    return response.result!!.terms
}