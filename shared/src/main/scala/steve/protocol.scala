package steve

object protocol {
  import sttp.tapir.*
  import sttp.tapir.json.circe.*

  // implicit def validate[A]: Validator[A] = Validator.pass

  private val base = infallibleEndpoint.in("api")

  val build: Endpoint[Build, Nothing, Hash, Any] = base
    .put
    .in("build")
    .in(jsonBody[Build])
    .out(jsonBody[Hash])

  // curl -X PUT locahost:8080/api/build -d '{"name": "My Build"}'

  val run: Endpoint[Hash, Nothing, SystemState, Any] = base
    .post
    .in("run")
    .in(jsonBody[Hash])
    .out(jsonBody[SystemState])

}
