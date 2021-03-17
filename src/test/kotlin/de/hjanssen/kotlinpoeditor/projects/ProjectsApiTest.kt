package de.hjanssen.kotlinpoeditor.projects

import de.hjanssen.kotlinpoeditor.mockApiResponse
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.ints.shouldBeExactly
import io.kotest.matchers.shouldBe

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
                            "public": 1,
                            "open": 0,
                            "created": "2013-04-12T09:24:42+0000"
                        },
                        {
                            "id": 4537,
                            "name": "contact form",
                            "public": 0,
                            "open": 1,
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
            firstProject.id shouldBeExactly 4886
            firstProject.name shouldBe "Twentytwelve"
            firstProject.public shouldBe true
            firstProject.open shouldBe false
            firstProject.created.hour shouldBeExactly 9
            firstProject.created.year shouldBeExactly 2013

            val secondProject = result[1]
            secondProject.id shouldBeExactly 4537
            secondProject.name shouldBe "contact form"
            secondProject.public shouldBe false
            secondProject.open shouldBe true
            secondProject.created.hour shouldBeExactly 23
            secondProject.created.year shouldBeExactly 2013
        }
    }
})