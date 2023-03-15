package models

enum RegularExpression {
  case OR(a: RegularExpression, b: RegularExpression) extends RegularExpression
  case AND(a: RegularExpression, b: RegularExpression) extends RegularExpression
  case IS(a: Char) extends RegularExpression
  case Sigma(a: RegularExpression) extends RegularExpression
}
