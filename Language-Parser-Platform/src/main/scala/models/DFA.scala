package models

final case class DFA(
  startingNode: Node,
  terminalNode: Node,
  associations: Map[Node, Neighbors[Char]]
)
