package com.softwaremill

import sttp.tapir.server.netty.NettyFutureServer

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, ExecutionContext, Future}
import scala.io.StdIn
import ExecutionContext.Implicits.global

object Main {


  def main(args: Array[String]): Unit = {
    println("What do you want to do?")
    println("Press one for running SwaggerUI")
    println("Press two for printing the OpenAPI specification")

    StdIn.readInt() match {
      case 1 => runSwaggerUI()
      case 2 => printOpenAPI()
      case _ => println("Invalid input")
    }
  }

  def runSwaggerUI(): Unit = {

    val port = sys.env.get("HTTP_PORT").flatMap(_.toIntOption).getOrElse(8080)
    val program = for {
      binding <- NettyFutureServer().port(port).addEndpoints(EndpointsSwagger.all).start()
      _ <- Future {
        println(s"Go to http://localhost:${binding.port}/docs to open SwaggerUI. Press ENTER key to exit.")
        StdIn.readLine()
      }
      stop <- binding.stop()
    } yield stop

    Await.result(program, Duration.Inf)
  }

  def printOpenAPI(): Unit = {

    println("YAML: \n" + EndpointsOpenAPI.yaml)
    println("JSON: \n" + EndpointsOpenAPI.json)

  }
}
