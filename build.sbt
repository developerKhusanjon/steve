val Versions =
  new {
    val tapir = "0.19.0-M9"
    val http4s = "0.23.3"
  }

ThisBuild / scalaVersion := "3.3.2-RC3"
ThisBuild / versionScheme := Some("early-semver")
ThisBuild / githubWorkflowPublishTargetBranches := Seq()

val commonSettings = Seq(
  scalacOptions -= "-Xfatal-warnings",
  libraryDependencies ++= Seq(
    "org.typelevel" %% "cats-effect" % "3.5.0-RC3",
    // "org.typelevel" %% "cats-mtl" % "1.4.0",
    "org.typelevel" %% "munit-cats-effect-3" % "1.0.7",
  ),
)

val shared = project.settings(
  commonSettings,
  libraryDependencies ++= Seq(
    "com.softwaremill.sttp.tapir" %% "tapir-core" % Versions.tapir,
    "com.softwaremill.sttp.tapir" %% "tapir-json-circe" % Versions.tapir,
    "com.softwaremill.sttp.tapir" %% "tapir-http4s-server" % Versions.tapir,
  ),
)

val server = project
  .settings(
    commonSettings,
    libraryDependencies ++= Seq(
      "com.softwaremill.sttp.tapir" %% "tapir-http4s-server" % Versions.tapir,
      "org.http4s" %% "http4s-dsl" % Versions.http4s,
      "org.http4s" %% "http4s-ember-server" % Versions.http4s,
      "ch.qos.logback" % "logback-classic" % "1.5.1",
    ),
  )
  .dependsOn(shared)

val client = project
  .settings(
    commonSettings,
    libraryDependencies ++= Seq(
      "org.http4s" %% "http4s-ember-client" % Versions.http4s,
      "com.softwaremill.sttp.tapir" %% "tapir-http4s-client" % Versions.tapir,
    ),
  )
  .dependsOn(shared)

val root = project
  .in(file("."))
  .settings(publish := {}, publish / skip := true)
  .aggregate(server, client, shared)
