package com.janikdotzel

import sttp.apispec.openapi.OpenAPI
import sttp.tapir._
import sttp.tapir.docs.openapi.OpenAPIDocsInterpreter
import sttp.apispec.openapi.circe.yaml._
import io.circe.syntax._
import sttp.apispec.openapi.circe._
import sttp.tapir.server.ServerEndpoint

import scala.concurrent.Future

object EndpointsOpenAPI {

  case class User(name: String) extends AnyVal

  val helloEndpoint = endpoint.get
    .in("hello")
    .in(query[User]("name"))
    .out(stringBody)

  val helloServerEndpoint =
    helloEndpoint.serverLogicSuccess(user => Future.successful(s"Hello ${user.name}"))

  val openApi: OpenAPI = OpenAPIDocsInterpreter()
    .toOpenAPI(helloServerEndpoint, "My Bookshop", "1.0")

  val yaml: String = openApi.toYaml
  val json: String = openApi.asJson.spaces2
}
