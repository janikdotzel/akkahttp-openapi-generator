package com.janikdotzel

import sttp.tapir._

import scala.concurrent.Future

object TapirEndpoints {

  case class User(name: String) extends AnyVal

  val helloEndpoint = endpoint.get
    .in("hello")
    .in(query[User]("name"))
    .out(stringBody)

  val helloServerEndpoint =
    helloEndpoint.serverLogicSuccess(user => Future.successful(s"Hello ${user.name}"))

}
