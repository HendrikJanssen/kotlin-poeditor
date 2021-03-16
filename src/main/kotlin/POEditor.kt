import api.POEditorApi
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import projects.Project
import terms.Term

class POEditor(private val api: POEditorApi) {

    constructor(baseUrl: String, token: String) : this(POEditorApi(baseUrl, token, jacksonObjectMapper().registerModule(JavaTimeModule())))

    fun listProjects(): List<Project> = projects.listProjects(api)
    fun viewProject(projectId: Int): Project = projects.viewProject(api, projectId)

    fun listTerms(projectId: Int, language: String? = null): List<Term> = terms.listTerms(api, projectId, language)

}