package steve

trait Executor[F[_]] {
  def build(build: Build): F[Hash]
  def run(hash: Hash): F[SystemState]
}

object Executor {

  def apply[F[_]](
    using f: Executor[F]
  ): Executor[F] = f

}
