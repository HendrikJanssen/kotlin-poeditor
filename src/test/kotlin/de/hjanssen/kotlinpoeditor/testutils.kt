package de.hjanssen.kotlinpoeditor

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import de.hjanssen.kotlinpoeditor.api.POEditorApi
import io.ktor.client.*
import io.ktor.client.engine.mock.*
import io.ktor.client.features.json.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import io.ktor.http.*

fun mockApiResponse(response: String): POEditorApi {
    return POEditorApi("", "", mockHttpClient {
        val headers = headersOf("Content-Type" to listOf(ContentType.Application.Json.toString()))
        respond(response, headers = headers)
    })
}

fun mockHttpClient(handler: suspend MockRequestHandleScope.(HttpRequestData) -> HttpResponseData): HttpClient {
    return HttpClient(MockEngine) {
        engine {
            addHandler(handler)
        }

        install(JsonFeature) {
            serializer = JacksonSerializer(jacksonObjectMapper().registerModule(JavaTimeModule()))
        }

        install(Logging) {
            level = LogLevel.ALL
        }
    }
}