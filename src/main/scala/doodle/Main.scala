package doodle

import doodle.jvm.Java2DCanvas
import main.scala.doodle.core.Circle

object Main extends App {
  import doodle.core._
  import doodle.jvm.Java2DCanvas
  import doodle.example._
  import javax.swing.JFrame

  val canvas = Java2DCanvas.canvas
  canvas.panel.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)

  Spiral.draw(canvas)
}

object MaxTest extends App {
  val canvas = Java2DCanvas.canvas

  Circle(10).draw(canvas)
}