package de.hjanssen.kotlinpoeditor

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import de.hjanssen.kotlinpoeditor.api.POEditorApi
import de.hjanssen.kotlinpoeditor.projects.Project
import de.hjanssen.kotlinpoeditor.terms.Term
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.json.*

class POEditor(private val api: POEditorApi) {

    companion object {
        internal fun createHttpClient(engine: CIO = CIO) = HttpClient(engine) {
            install(JsonFeature) {
                serializer = JacksonSerializer(jacksonObjectMapper().registerModule(JavaTimeModule()))
            }
        }
    }

    constructor(baseUrl: String, token: String) : this(POEditorApi(baseUrl, token, createHttpClient()))

    constructor(token: String) : this("https://api.poeditor.com", token)

    constructor(token: String, httpClient: HttpClient) : this(POEditorApi("https://api.poeditor.com", token, httpClient))

    suspend fun listProjects(): List<Project> = de.hjanssen.kotlinpoeditor.projects.listProjects(api)
    suspend fun viewProject(projectId: Int): Project = de.hjanssen.kotlinpoeditor.projects.viewProject(api, projectId)

    suspend fun listTerms(projectId: Int, language: String? = null): List<Term> =
        de.hjanssen.kotlinpoeditor.terms.listTerms(api, projectId, language)

}