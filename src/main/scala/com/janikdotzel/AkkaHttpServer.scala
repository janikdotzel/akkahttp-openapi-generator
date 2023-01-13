package com.janikdotzel

import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._

import scala.concurrent.ExecutionContextExecutor
import scala.io.StdIn

object AkkaHttpServer {

  def main(args: Array[String]): Unit = {

    implicit val system: ActorSystem[Nothing] = ActorSystem(Behaviors.empty, "my-system")
    // needed for the future flatMap/onComplete in the end
    implicit val executionContext: ExecutionContextExecutor = system.executionContext

    val route = {
      concat(
        path("hello") {
          get {
            parameter("name") { name =>
              complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, s"<h1>Hello $name</h1>"))
            }
          }
        },
        path("bye") {
          get {
            parameter("name") { name =>
              complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, s"<h1>Bye $name</h1>"))
            }
          }
        }
      )
    }

    val bindingFuture = Http().newServerAt("localhost", 8080).bind(route)

    println(s"Server now online. Please navigate to http://localhost:8080/hello\nPress RETURN to stop...")
    StdIn.readLine() // let it run until user presses return
    bindingFuture
      .flatMap(_.unbind()) // trigger unbinding from the port
      .onComplete(_ => system.terminate()) // and shutdown when done
  }
}
