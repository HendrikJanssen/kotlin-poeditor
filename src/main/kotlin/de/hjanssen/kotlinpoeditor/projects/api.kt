package de.hjanssen.kotlinpoeditor.projects

import de.hjanssen.kotlinpoeditor.api.POEditorApi
import de.hjanssen.kotlinpoeditor.api.POEditorResponse

internal data class ListProjectsResponse(
    val projects: List<Project>
)

suspend fun listProjects(api: POEditorApi): List<Project> {

    val response = api.requestApi<POEditorResponse<ListProjectsResponse>, ListProjectsResponse>("/v2/projects/list")

    return response.projects
}

internal data class ViewProjectResponse(
    val project: Project
)

suspend fun viewProject(api: POEditorApi, projectId: Int): Project {

    val response =
        api.requestApi<POEditorResponse<ViewProjectResponse>, ViewProjectResponse>("/v2/projects/view", listOf("id" to "$projectId"))

    return response.project
}