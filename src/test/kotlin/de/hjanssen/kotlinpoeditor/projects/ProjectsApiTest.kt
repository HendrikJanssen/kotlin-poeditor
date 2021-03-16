package de.hjanssen.kotlinpoeditor.projects

import de.hjanssen.kotlinpoeditor.mockApiResponse
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.ints.shouldBeExactly

class ProjectsApiTest : ShouldSpec({
    context("Projects serialization") {
        should("correctly serialize listProjects response") {
            val api = mockApiResponse(
                """
                {
                    "response": {
                    "status": "success",
                    "code": "200",
                    "message": "OK"
                },
                "result": {
                    "projects": [
                        {
                            "id": 4886,
                            "name": "Twentytwelve",
                            "public": 0,
                            "open": 0,
                            "created": "2013-04-12T09:24:42+0000"
                        },
                        {
                            "id": 4537,
                            "name": "contact form",
                            "public": 0,
                            "open": 0,
                            "created": "2013-04-02T23:00:06+0000"
                        }
                    ]
                }
            }
            """.trimIndent()
            )

            val result = listProjects(api)

            result.size shouldBeExactly 2

            val firstProject = result[0]

            val secondProject = result[1]
        }
    }
})