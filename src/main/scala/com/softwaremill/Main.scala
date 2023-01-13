package com.softwaremill

import sttp.tapir.server.netty.NettyFutureServer

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, ExecutionContext, Future}
import scala.io.StdIn
import ExecutionContext.Implicits.global

object Main {
  def main(args: Array[String]): Unit = {

    val port = sys.env.get("HTTP_PORT").flatMap(_.toIntOption).getOrElse(8080)
    val program = for {
      binding <- NettyFutureServer().port(port).addEndpoints(Endpoints.all).start()
      _ <- Future {
        println(s"Go to http://localhost:${binding.port}/docs to open SwaggerUI. Press ENTER key to exit.")
        StdIn.readLine()
      }
      stop <- binding.stop()
    } yield stop

    Await.result(program, Duration.Inf)
  }
}
