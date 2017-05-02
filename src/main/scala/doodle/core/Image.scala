package main.scala.doodle.core

import doodle.backend.Canvas

sealed trait Image {
  def on(that: Image) : Image = On(this, that)

  def beside(that: Image) : Image = Beside(this, that)

  def above(that: Image) : Image = Above(this, that)

  def draw(canvas: Canvas) = draw(canvas, 0, 0)

  def draw(canvas: Canvas, originX: Double, originY: Double) : Unit = this match {
      case Circle(radius) => canvas.circle(0, 0, radius)
      case Rectangle(width, height) => canvas.rectangle(- width / 2, height / 2, width / 2, - height / 2)
      case On(above, below) =>
        above.draw(canvas, originX, originY + this.boundingBox.top - above.boundingBox.height / 2)
        below.draw(canvas, originX, originY + this.boundingBox.bottom + below.boundingBox.height / 2)
      case Beside(left, right) =>
        left.draw(canvas, originX + this.boundingBox.left - left.boundingBox.width / 2, originY)
        right.draw(canvas, originX - this.boundingBox.right + right.boundingBox.width / 2, originY)
      case Above(upper, lower) =>
        upper.draw(canvas, originX, originY)
        lower.draw(canvas, originX, originY)
    }

  def boundingBox : BoundingBox = this match {
    case Circle(radius) => BoundingBox(-radius, radius, -radius, radius)
    case Rectangle(width, height) => BoundingBox(-width / 2, width / 2, -height / 2, height / 2)
    case On(above, below) => above.boundingBox.on(below.boundingBox)
    case Beside(left, right) => left.boundingBox.beside(right.boundingBox)
    case Above(upper, lower) => upper.boundingBox.above(lower.boundingBox)
  }

}

final case class Circle(radius : Double) extends Image
final case class Rectangle(width: Double, height: Double) extends Image
final case class On(above: Image, below: Image) extends Image
final case class Beside(left: Image, right: Image) extends Image
final case class Above(upper: Image, lower: Image) extends Image