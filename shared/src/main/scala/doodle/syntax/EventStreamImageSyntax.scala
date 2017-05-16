package doodle.syntax

import doodle.backend.Canvas
import doodle.core.{Color, Image}
import doodle.event.EventStream

/**
 * Created by Max on 5/15/2017.
 */
trait EventStreamImageSyntax {
  implicit class ToEventStreamImage(val eventStream: EventStream[Image]) {
    def animate(implicit canvas: Canvas) = eventStream.map(frame => {
      canvas.clear(Color.black)
      frame.draw(canvas)
    })
  }
}
