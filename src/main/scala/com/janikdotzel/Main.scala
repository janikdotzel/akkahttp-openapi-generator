package com.janikdotzel

import akka.http.scaladsl.server.Route
import com.janikdotzel.TapirEndpoints.helloServerEndpoint
import sttp.apispec.openapi.OpenAPI
import sttp.apispec.openapi.circe.yaml.RichOpenAPI
import sttp.tapir.docs.openapi.OpenAPIDocsInterpreter
import sttp.tapir.server.akkahttp.AkkaHttpServerInterpreter
import io.circe.syntax._
import sttp.apispec.openapi.circe._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object Main extends App {

  // Start HTTP server
  Future {
    AkkaHttpServer.start()
  }

  // Akka HTTP Endpoints
  val akkaEndpoints = AkkaHttpServer.route

  // Tapir Endpoint
  val tapirEndpoints = TapirEndpoints.helloServerEndpoint

  // Convert to OpenAPI
  val openApi: OpenAPI = OpenAPIDocsInterpreter()
    .toOpenAPI(helloServerEndpoint, "Hello World", "1.0")

  val swaggerRoute: Route = AkkaHttpServerInterpreter()
    .toRoute(tapirEndpoints)


    println("YAML: \n" + openApi.toYaml)
    println("JSON: \n" + openApi.asJson.spaces2)

}