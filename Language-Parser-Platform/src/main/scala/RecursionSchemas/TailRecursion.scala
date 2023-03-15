package recursion

abstract class FiniteTailRecursion[A, B] {
  def recurse: B
}

object FiniteTailRecursion {
  def apply[A, B](init: A, emptyAcc: B, reduce: (A, B) => (A, B), terminalCondition: A => Boolean): FiniteTailRecursion[A, B] = new FiniteTailRecursion[A, B] {
    override def recurse: B = {

      def recurseTail(next: A, acc: B): B = {
        val (next_, acc_) = reduce(next, acc)
        if(terminalCondition(next_)) {
          acc
        } else {
          recurseTail(next, acc_)
        }
      }

      if(terminalCondition(init)) {
        emptyAcc
      } else {        
        recurseTail(init, emptyAcc)
      }
      
    }
  }
}
