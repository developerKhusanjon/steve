package steve

import munit.CatsEffectSuite

class ServerSideExecutorTests extends CatsEffectSuite {
  val exec = ServerSideExecutor.instance[Either[Throwable, *]]

  test("Build and Run empty image") {

    assertEquals(
      exec.build(Build.empty).flatMap(exec.run).map(_.getAll),
      Right(Map.empty),
    )
  }

}
