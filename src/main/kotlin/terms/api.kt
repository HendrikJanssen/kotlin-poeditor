package terms

import api.POEditorApi

internal data class ListTermsResponse(
    val terms: List<Term>
)

fun listTerms(api: POEditorApi, projectId: Int, language: String?): List<Term> {

    var parameters = listOf("id" to "$projectId")

    language?.let {
        parameters = parameters + listOf("language" to language)
    }

    val response = api.requestApi<ListTermsResponse>("/v2/terms/list", parameters)
    return response.terms
}