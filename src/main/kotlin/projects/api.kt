package projects

import api.POEditorApi

internal data class ListProjectsResponse(
    val projects: List<Project>
)

fun listProjects(api: POEditorApi): List<Project> {

    val response = api.requestApi<ListProjectsResponse>("/v2/projects/list")

    return response.projects
}

internal data class ViewProjectResponse(
    val project: Project
)

fun viewProject(api: POEditorApi, projectId: Int): Project {

    val response = api.requestApi<ViewProjectResponse>("/v2/projects/view", listOf("id" to "$projectId"))

    return response.project
}