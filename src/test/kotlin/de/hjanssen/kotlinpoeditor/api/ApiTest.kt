package de.hjanssen.kotlinpoeditor.api

import de.hjanssen.kotlinpoeditor.mockHttpClient
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain
import io.ktor.client.engine.mock.*
import io.ktor.http.*

class ApiTest : ShouldSpec({
    context("Api wrapper") {
        val client = mockHttpClient { request ->

            request.body.contentType.toString() shouldContain ContentType.MultiPart.FormData.toString()

            respond(
                """
                {
                    "response": {
                        "status": "success",
                        "code": "200",
                        "message": "OK"
                    },
                    "result": "test"
                }
            """.trimIndent()
            , headers = headersOf("Content-Type" to listOf(ContentType.Application.Json.toString())))
        }

        val api = POEditorApi("http://localhost", "TEST_TOKEN", client)

        should("add the api token to the form data") {

            val data = api.requestApi<POEditorResponse<String>>("/api/test", null)

            data.result shouldBe "test"
        }
    }
})