package codes.arnold.matt.mockapp

import codes.arnold.matt.mockapp.endpoint.MockEndpoint
import codes.arnold.matt.mockapp.ui.UiModel
import codes.arnold.matt.mockapp.ui.UiModelMapper
import codes.arnold.matt.mockapp.variable.MockVariable
import io.ktor.application.call
import io.ktor.freemarker.FreeMarkerContent
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.get

class MockServer(
    private val routing: Routing,
    private val mapper: UiModelMapper
) {

    private val groups = mutableSetOf<MockGroup>()

    fun group(name: String, configuration: MockGroup.() -> Unit) {

        val group = MockGroup(name, this).apply(configuration)
        groups.add(group)
        group.endpoints.forEach {
            it.setDefaultSelectedResponse()
            routing.apply(it.routes)
        }
        group.variables.forEach { it.setDefault() }
    }

    fun getUiModel(): UiModel {
        return mapper.map("Mock Server", groups)
    }
}

class MockGroup(val name: String, private val server: MockServer) {

    private val _endpoints = mutableSetOf<MockEndpoint>()
    private val _variables = mutableSetOf<MockVariable<*>>()

    val endpoints: Set<MockEndpoint>
        get() = _endpoints

    val variables: Set<MockVariable<*>>
        get() = _variables

    fun endpoint(endpoint: MockEndpoint) {
        _endpoints.add(endpoint)
    }

    fun variable(variable: MockVariable<*>) {
        _variables.add(variable)
    }
}

fun Routing.mockServer(configuration: MockServer.() -> Unit): MockServer {

    val uiMapper = UiModelMapper()
    val mockServer = MockServer(this, uiMapper).apply(configuration)

    get("/") {
        call.respond(FreeMarkerContent("index.ftl", mapOf("model" to mockServer.getUiModel())))
    }
    return mockServer
}