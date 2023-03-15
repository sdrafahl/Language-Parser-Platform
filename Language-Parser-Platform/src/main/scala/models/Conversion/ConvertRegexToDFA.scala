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

            val newEdge = Edge(EmptyChar())
            val newNeighbor = Neighbors(Set((newEdge, secondNFA.startingNode)))

            val newAssociation = Map(firstNFA.terminalNode -> newNeighbor)

            (
              inc3 + 1,
              NFA(
                firstNFA.startingNode,
                secondNFA.terminalNode,
                firstNFA.associations ++ secondNFA.associations ++ newAssociation
              )
            )
          }
          case RegularExpression.OR(first, second) => ???

        }
      }      
    }
  }
}
