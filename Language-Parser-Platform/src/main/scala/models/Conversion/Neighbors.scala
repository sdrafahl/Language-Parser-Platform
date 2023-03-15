package models

final case class Neighbors[A](x: Set[(Edge[A], Node)])

object Neighbors {
  def noNeighbors[A] = Neighbors(Set.empty[(Edge[A], Node)])
}
