package com.janikdotzel

import akka.http.scaladsl.server.Route
import sttp.tapir.server.akkahttp.AkkaHttpServerInterpreter

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object Main extends App {

  Future {
    AkkaHttpServer.start()
  }

  val route = AkkaHttpServer.route

  val swaggerRoute: Route = AkkaHttpServerInterpreter()
    .toRoute(EndpointsOpenAPI.helloServerEndpoint)


    println("YAML: \n" + EndpointsOpenAPI.yaml)
    println("JSON: \n" + EndpointsOpenAPI.json)

}