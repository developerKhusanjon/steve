package steve

import munit.CatsEffectSuite
import cats.Id

class ExecutorTests extends CatsEffectSuite {
  val exec = Executor.instance[Id]

  test("Build and Run empty image") {
    val hash = exec.build(Build.empty)

    assertEquals(
      exec.run(hash).getAll,
      Map.empty,
    )
  }

}
