package com.softwaremill

import com.softwaremill.EndpointsSwagger._
import org.scalatest.EitherValues
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import sttp.client3.testing.SttpBackendStub
import sttp.client3.{UriContext, basicRequest}
import sttp.tapir.server.stub.TapirStubInterpreter

import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.concurrent.duration.Duration

class EndpointsSwaggerSpec extends AnyFlatSpec with Matchers with EitherValues {

  it should "return hello message" in {
    // given
    val backendStub = TapirStubInterpreter(SttpBackendStub.asynchronousFuture)
      .whenServerEndpoint(helloServerEndpoint)
      .thenRunLogic()
      .backend()

    // when
    val response = basicRequest
      .get(uri"http://test.com/hello?name=adam")
      .send(backendStub)

    // then
    response.map(_.body.value shouldBe "Hello adam").unwrap
  }

  implicit class Unwrapper[T](t: Future[T]) {
    def unwrap: T = Await.result(t, Duration.Inf)
  }
}
