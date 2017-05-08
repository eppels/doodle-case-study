package random

import scala.util

trait Random[A] {
  def run(rand: scala.util.Random) : A

  def map[B](f: A => B) : Random[B] = {
    Random.always(f(run(scala.util.Random)))
  }

  def flatMap[B](f : A => Random[B]) : Random[B] = {
    f(run(scala.util.Random))
  }

  def zip[B](that: Random[B]) : Random[(A, B)] = for {
    a <- this
    b <- that
  } yield (a, b)
}


object Random {
  val double = new Random[Double] {
    override def run(rand: util.Random): Double = rand.nextDouble()
  }
  val int = new Random[Int] {
    override def run(rand: util.Random): Int = rand.nextInt()
  }
  val normal : Random[Double] = new Random[Double] {
    override def run(rand: util.Random): Double = rand.nextGaussian()
  }
  def always[A](in: A) : Random[A] = new Random[A] {
    override def run(rand: util.Random): A = in
  }
}