package scanner

import scala.collection.immutable.LazyList

abstract class Reader[L[_]] {
  def readOnce(stream: L[Char]): L[Char]
}
