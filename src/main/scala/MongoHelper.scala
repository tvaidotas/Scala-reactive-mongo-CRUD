import org.mongodb.scala.{Completed, Document, Observer}

object MongoHelper {

  val observeInsert: Observer[Completed] = new Observer[Completed] {
    override def onNext(result: Completed): Unit = println(s"Inserted")

    override def onError(error: Throwable): Unit = println(s"Failed $error")

    override def onComplete(): Unit = println("Completed")
  }

  // Example document _id has to be unique
  val doc: Document = Document(
    "_id" -> 1,
    "name" -> "MongoDB",
    "type" -> "database",
    "count" -> 1,
    "info" -> Document(
      "x" -> 203,
      "y" -> 102)
  )
  // Example document _id has to be unique
  val doc1: Document = Document(
    "_id" -> 2,
    "name" -> "MongoDB",
    "type" -> "database",
    "count" -> 1,
    "info" -> Document(
      "x" -> 203,
      "y" -> 102)
  )
  // Example document _id has to be unique
  val doc2: Document = Document(
    "_id" -> 3,
    "name" -> "MongoDB",
    "type" -> "database",
    "count" -> 1,
    "info" -> Document(
      "x" -> 203,
      "y" -> 102)
  )

  // Example documents collection _id would be uniques as mongodb would create it itself
  val documents: Seq[Document] = (1 to 10) map { i: Int => Document("i" -> i) }

}
