package com.janikdotzel

import sttp.apispec.openapi.OpenAPI
import sttp.tapir._
import sttp.tapir.docs.openapi.OpenAPIDocsInterpreter
import sttp.apispec.openapi.Server
import sttp.apispec.openapi.circe.yaml._
import io.circe.Printer
import io.circe.syntax._
import sttp.apispec.openapi.circe._

object EndpointsOpenAPI {

  val booksListingEndpoint: Endpoint[Unit, String, Unit, Unit, Any] = endpoint.in(path[String]("bookId"))

  val docsWithServers: OpenAPI = OpenAPIDocsInterpreter()
    .toOpenAPI(booksListingEndpoint, "My Bookshop", "1.0")
    .servers(List(Server("https://api.example.com/v1")))

  val yaml: String = docsWithServers.toYaml
  val json: String = docsWithServers.asJson.spaces2
}
