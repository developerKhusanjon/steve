val commonSettings = Seq(
  scalaVersion := "3.3.2-RC3",
  scalacOptions -= "-Xfatal-warnings",
  libraryDependencies ++= Seq(
    "org.typelevel" %% "cats-effect" % "3.5.0-RC3",
    // "org.typelevel" %% "cats-mtl" % "1.4.0",
    "org.typelevel" %% "munit-cats-effect-3" % "1.0.7",
  ),
)

val shared = project.settings(commonSettings)

val server = project.settings(commonSettings)

val client = project.settings(commonSettings)

val root = project
  .in(file("."))
  .settings(publish := {})
  .aggregate(server, client, shared)
