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
    "com.softwaremill.sttp.tapir" %% "tapir-core" % "1.9.11",
    "com.softwaremill.sttp.tapir" %% "tapir-json-circe" % "1.9.11",
    "com.softwaremill.sttp.tapir" %% "tapir-http4s-server" % "1.9.11",
  ),
)

val server = project
  .settings(
    commonSettings,
    libraryDependencies ++= Seq(
      "com.softwaremill.sttp.tapir" %% "tapir-http4s-server" % "1.9.11",
      "org.http4s" %% "http4s-dsl" % "0.23.3",
      "org.http4s" %% "http4s-ember-server" % "0.23.3",
    ),
  )
  .dependsOn(shared)

val client = project.settings(commonSettings).dependsOn(shared)

val root = project
  .in(file("."))
  .settings(publish := {}, publish / skip := true)
  .aggregate(server, client, shared)
