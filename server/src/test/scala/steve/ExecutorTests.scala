package steve

import munit.CatsEffectSuite

class ExecutorTests extends CatsEffectSuite {
  val exec = Executor.instance[Either[Throwable, *]]

  test("Build and Run empty image") {

    assertEquals(
      exec.build(Build.empty).flatMap(exec.run).map(_.getAll),
      Right(Map.empty),
    )
  }

}
