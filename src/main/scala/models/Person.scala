package models

import org.mongodb.scala.bson.ObjectId

object Person {
  def apply(firstName: String, lastName: String): Person =
    Person(new ObjectId(), firstName, lastName)
}
case class Person(_id: ObjectId, firstName: String, lastName: String)
