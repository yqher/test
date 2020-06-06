package test

object T {
  def main(args: Array[String]): Unit = {
    val o1: Student = new Student
    val o2: Person = new Student
    println(s"stdent:${o1.name}---person:${o2.name}")

  }
}

abstract class Person {
  val name: String

}

class Student extends Person {
  override val name: String = "student"
}
