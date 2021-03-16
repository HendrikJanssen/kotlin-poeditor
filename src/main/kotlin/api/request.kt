package api

import com.fasterxml.jackson.databind.ObjectMapper
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.Method
import com.github.kittinunf.result.Result


data class POEditorResponseStatus(
    val status: String,
    val code: Int,
    val message: String
)

data class POEditorResponse<T>(
    val response: POEditorResponseStatus,
    val result: T?
)

class POEditorApi(val baseUrl: String, val token: String, val objectMapper: ObjectMapper) {
    inline fun <reified T> requestApi(path: String, parameters: List<Pair<String, String>> = listOf()): T {

        val result = Fuel.upload("$baseUrl$path", Method.POST, listOf("api_token" to token) + parameters)
            .responseString().third

        when (result) {
            is Result.Failure -> {
                val ex = result.getException()

                val responseData = parseResult<Void>(ex.response.toString())

                throw POEditorException(responseData.response.code, responseData.response.message)
            }
            is Result.Success -> {
                val data = result.get()

                val responseData = parseResult<T>(data)

                if (responseData.result != null) {
                    return responseData.result
                }

                throw POEditorException(responseData.response.code, responseData.response.message)
            }
        }
    }

    inline fun <reified T> parseResult(json: String): POEditorResponse<T> {
        val type = objectMapper.typeFactory.constructParametricType(POEditorResponse::class.java, T::class.java)
        return objectMapper.readValue(json, type)
    }
}

