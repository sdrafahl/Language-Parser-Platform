package models

import recursion._

abstract class ConvertRegexToNFA extends Converter[RegularExpression, NFA]

object ConvertRegexToNFA {
  given ConvertRegexToNFA = new ConvertRegexToNFA {
    def convert(a: RegularExpression): NFA = {

      val startNode = Node(0)
      val initialNFA = NFA(
        startNode,
        Node(-1),
        Map(startNode -> Neighbors.noNeighbors)
      )      

      def convertRec(a: RegularExpression, inc: Int): (Int, NFA) = {
        a match {
          case RegularExpression.AND(first, second) => {
            val (inc2, firstNFA) = convertRec(first, inc)
            val (inc3, secondNFA) = convertRec(second, inc2)

            val newEdge = Edge[Char | models.EmptyChar](EmptyChar())
            val newNeighbor = Neighbors(Set((newEdge, secondNFA.startingNode)))

            val newAssociation: Map[models.Node, models.Neighbors[Char | models.EmptyChar]] = Map(firstNFA.terminalNode -> newNeighbor)

            (
              inc3 + 1,
              NFA(
                firstNFA.startingNode,
                secondNFA.terminalNode,
                firstNFA.associations ++ secondNFA.associations ++ newAssociation
              )
            )
          }
          case RegularExpression.OR(first, second) => {

            val (inc2, firstNfa) = convertRec(first, inc)
            val (inc3, secondNfa) = convertRec(second, inc2)

            val newStartNode = Node(inc3 + 1)
            val newTerminalNode = Node(inc3 + 2)

            val newNeighbor1 = Set(
              (Edge[Char | models.EmptyChar](EmptyChar()), firstNfa.startingNode)
            )

            val newNeighbor2 = Set(
              (Edge[Char | models.EmptyChar](EmptyChar()), secondNfa.startingNode)
            )

            val newAssociations  = Map(
              newStartNode -> Neighbors[Char | EmptyChar](newNeighbor1 ++ newNeighbor2),
              firstNfa.terminalNode -> Neighbors[Char | EmptyChar](Set((Edge[Char | models.EmptyChar](EmptyChar()), newTerminalNode))),
              secondNfa.terminalNode -> Neighbors[Char | EmptyChar](Set((Edge[Char | models.EmptyChar](EmptyChar()), newTerminalNode)))
            )
            (
              inc + 3,
              NFA(
                newStartNode,
                newTerminalNode,
                firstNfa.associations ++ secondNfa.associations ++ newAssociations
              )
            )

          }
          case RegularExpression.Sigma(regular) => ???
        }
      }      
    }
  }
}
