package main.scala.doodle.core

import Math._

case class BoundingBox(left: Double, right: Double, bottom: Double, top: Double) {
  val height = top - bottom

  val width = right - left

  def on(that: BoundingBox) = BoundingBox(min(this.left, that.left), max(this.right, that.right), min(this.bottom, that.bottom), max(this.top, that.top))

  def beside(that: BoundingBox) = BoundingBox(-(this.width - that.width) / 2, (this.width + that.width) / 2, min(this.bottom, that.bottom), max(this.top, that.top))

  def above(that: BoundingBox) = BoundingBox(min(this.left, that.left), max(this.right, that.right), -(this.height + that.height) / 2, (this.height + that.height) / 2)

}
