package com.softwaremill

import sttp.tapir._

import scala.concurrent.Future
import sttp.tapir.server.ServerEndpoint
import sttp.tapir.swagger.bundle.SwaggerInterpreter

object Endpoints {
  case class User(name: String) extends AnyVal
  val helloEndpoint: PublicEndpoint[User, Unit, String, Any] = endpoint.get
    .in("hello")
    .in(query[User]("name"))
    .out(stringBody)
  val helloServerEndpoint: ServerEndpoint[Any, Future] = helloEndpoint.serverLogicSuccess(user => Future.successful(s"Hello ${user.name}"))

  val apiEndpoints: List[ServerEndpoint[Any, Future]] = List(helloServerEndpoint)

  val docEndpoints: List[ServerEndpoint[Any, Future]] = SwaggerInterpreter()
    .fromServerEndpoints[Future](apiEndpoints, "tapir-example", "1.0.0")

  val all: List[ServerEndpoint[Any, Future]] = apiEndpoints ++ docEndpoints
}
