val tapirVersion = "1.2.5"
lazy val akkaHttpVersion = "10.4.0"
lazy val akkaVersion = "2.7.0"

lazy val rootProject = (project in file(".")).settings(
  Seq(
    name := "tapir-example",
    version := "0.1.0-SNAPSHOT",
    organization := "com.janikdotzel",
    scalaVersion := "2.13.10",
    libraryDependencies ++= Seq(
      "com.softwaremill.sttp.tapir" %% "tapir-netty-server" % tapirVersion,
      "com.softwaremill.sttp.tapir" %% "tapir-swagger-ui-bundle" % tapirVersion,
      "com.softwaremill.sttp.tapir" %% "tapir-openapi-docs" % "1.2.5",
      "com.softwaremill.sttp.apispec" %% "openapi-circe-yaml" % "0.3.1",
      "com.softwaremill.sttp.tapir" %% "tapir-akka-http-server" % tapirVersion,
"ch.qos.logback" % "logback-classic" % "1.4.5",
      // Akka HTTP
      "com.typesafe.akka" %% "akka-actor-typed" % akkaVersion,
      "com.typesafe.akka" %% "akka-stream" % akkaVersion,
      "com.typesafe.akka" %% "akka-http" % akkaHttpVersion
    )
  )
)
