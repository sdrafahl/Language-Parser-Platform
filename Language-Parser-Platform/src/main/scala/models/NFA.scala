package models

final case class NFA(
  startingNode: Node,
  terminalNode: Node,
  associations: Map[Node, Neighbors[Char | EmptyChar]]
)
