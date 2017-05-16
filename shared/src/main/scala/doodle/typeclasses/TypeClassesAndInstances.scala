package doodle.typeclasses

/**
 * Created by Max on 5/15/2017.
 */
trait Functor[F[_]] {
  def map[A, B](fa: F[A])(f: A => B) : F[B]
}

trait Monad[F[_]] extends Functor[F[_]] {
  def flatMap[A, B](fa: F[A])(f: A => F[B]) : F[B]
  def point[A](a: A) : F[A]
}

trait Applicative[F[_]] extends Functor[F[_]] {
  def zip[A, B](fa: F[A], fb: F[B]) : F[(A, B)]
  def point[A](a : A) : F[A]
}

trait Scannable[F[_]] {
  def scanLeft[A, B](fa: F[A])(b: B)(f: (B, A) => B) : F[B]
}

object ListInstances {
  implicit object CoolList extends Functor[List] with Monad[List] with Applicative[List] with Scannable[List] {
    override def map[A, B](fa: List[A])(f: (A) => B): List[B] = fa.map(f)

    override def flatMap[A, B](fa: List[A])(f: (A) => List[B]): List[B] = fa.flatMap(f)

    override def point[A](a: A): List[A] = List(a)

    override def zip[A, B](fa: List[A], fb: List[B]): List[(A, B)] = fa.zip(fb)

    override def scanLeft[A, B](fa: List[A])(b: B)(f: (B, A) => B): List[B] = fa.scanLeft(b)(f)
  }
}

object Identity { //got pretty confused at this point, haven't seen cases to appreciate why this is useful.
  implicit class TheIdentity[T[_]](value: T) extends Functor[T] with Monad[T] with Applicative[T] with Scannable[T] {
    override def map[A, B](fa: T[A])(f: (A) => B): T[B] = ???

    override def flatMap[A, B](fa: T[A])(f: (A) => T[B]): T[B] = ???

    override def point[A](a: A): T[A] = ???

    override def zip[A, B](fa: T[A], fb: T[B]): T[(A, B)] = ???

    override def scanLeft[A, B](fa: T[A])(b: B)(f: (B, A) => B): T[B] = ???
  }
}