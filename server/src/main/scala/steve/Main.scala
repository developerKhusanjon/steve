package steve

import com.comcast.ip4s.port
import com.comcast.ip4s.host

import cats.effect.IOApp
import cats.effect.IO
import org.http4s.ember.server.EmberServerBuilder

object Main extends IOApp.Simple {

  def run: IO[Unit] =
    EmberServerBuilder
      .default[IO]
      .withHost(host"0.0.0.0")
      .withPort(port"8080")
      .build
      .useForever

}
