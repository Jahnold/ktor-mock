package codes.arnold.matt.mockapp.endpoint

import com.beust.klaxon.JsonBase
import com.beust.klaxon.Parser
import io.ktor.application.call
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.content.LocalFileContent
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.routing.Routing
import io.ktor.routing.get
import java.io.File

abstract class MockEndpoint {

    abstract val name: String
    abstract val values: List<EndpointValue>
    abstract val routes: Routing.() -> Unit

    lateinit var selectedResponse: EndpointValue
    var selectedResponseCode: HttpStatusCode = HttpStatusCode.OK

    protected fun defaultGetBehaviour(path: String): Routing.() -> Unit = {

        get(path) {
            val file = selectedResponse.toFile()

            if (file == null) {
                call.respond(HttpStatusCode.NotFound)
                return@get
            }

            val fileContent = LocalFileContent(
                contentType = ContentType.Application.Json,
                file = file
            )
            call.respond(selectedResponseCode, fileContent)
        }
    }

    protected fun <T: JsonBase> jsonGetBehaviour(path: String, modification: (json: T) -> T): Routing.() -> Unit = {

        get(path) {

            val json = selectedResponse.toJson() as T
            val newJson = modification(json)

            call.respondText(
                contentType = ContentType.Application.Json,
                text = newJson.toJsonString(prettyPrint = true),
                status = selectedResponseCode
            )
        }
    }

    fun setDefaultSelectedResponse() {
        selectedResponse = values.find { it.isDefault } ?: values.first()
    }
}

data class EndpointValue(
    val key: String,
    val json: String,
    val responseCode: HttpStatusCode,
    val isDefault: Boolean = false
)

fun EndpointValue.toJson(): JsonBase {
    return Parser.default().parse("resources/data/${json}") as JsonBase
}

fun EndpointValue.toFile(): File? {
    return File("resources/data/${json}").takeIf { it.exists() }
}
