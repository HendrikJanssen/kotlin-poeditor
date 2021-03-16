package de.hjanssen.kotlinpoeditor.api

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.http.*


data class POEditorResponseStatus(
    val status: String,
    val code: Int,
    val message: String
)

data class POEditorResponse<T>(
    val response: POEditorResponseStatus,
    val result: T?
)

class POEditorApi(val baseUrl: String, val token: String, val client: HttpClient) {

    suspend inline fun <reified T, reified V> requestApi(
        path: String,
        parameters: List<Pair<String, String>>? = null
    ): V where T : POEditorResponse<V> {

        val apiResponse = client.post<T> {
            url("$baseUrl$path")

            accept(ContentType.Application.Json)

            body = MultiPartFormDataContent(formData {
                append("api_token", token)

                parameters?.let { params ->
                    params.forEach {
                        append(it.first, it.second)
                    }
                }
            })
        }

        return when (apiResponse.response.status) {
            "success" -> apiResponse.result!!
            else -> throw POEditorException(apiResponse.response.code, apiResponse.response.message)
        }
    }
}

