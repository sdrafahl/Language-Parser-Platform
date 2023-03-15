package models

abstract class Converter[A, B] {
  def convert(a: A): B
}
