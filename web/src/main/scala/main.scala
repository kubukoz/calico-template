import calico.IOWebApp
import calico.html.io.*
import calico.html.io.given
import cats.effect.IO
import cats.effect.kernel.Resource
import fs2.dom.HtmlElement
import org.scalajs.dom.MouseEvent

object App extends IOWebApp {

  def render: Resource[IO, HtmlElement[IO]] = div(
    h2("hello there!"),
    "Mouse coords: ",
    fs2
      .dom
      .events[IO, MouseEvent](org.scalajs.dom.window, "mousemove")
      .map { e =>
        e.screenX -> e.screenY
      }
      .holdResource((0d, 0d))
      .map(_.map(_.toString)),
  )

}
