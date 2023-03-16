package models

opaque type EmptyChar = Unit

object EmptyChar {
  def apply(): EmptyChar = ()
}
