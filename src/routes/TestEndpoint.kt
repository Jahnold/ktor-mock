package codes.arnold.matt.routes

import codes.arnold.matt.mockapp.endpoint.EndpointValue
import codes.arnold.matt.mockapp.endpoint.MockEndpoint
import com.beust.klaxon.JsonObject
import io.ktor.http.HttpStatusCode

class TestEndpoint : MockEndpoint() {

    override val name = "TestRoute"

    override val values = listOf(
        EndpointValue(
            key = "success",
            json = "test/success.json",
            responseCode = HttpStatusCode.OK,
            isDefault = true
        ),
        EndpointValue(
            key = "failure",
            json = "test/failure.json",
            responseCode = HttpStatusCode.OK
        )
    )

    override val routes = jsonGetBehaviour<JsonObject>("test") { json ->
        json["value"] = "well hello"
        return@jsonGetBehaviour json
    }
}