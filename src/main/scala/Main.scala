import org.mongodb.scala._

object Main extends App {

  // Get connection
  val mongoClient: MongoClient = MongoClient("mongodb://localhost")
  // Get database
  val database: MongoDatabase = mongoClient.getDatabase("test")
  // Get collection
  val testCollection = database.getCollection("test")

  // Example document
  val doc: Document = Document("_id" -> 0, "name" -> "MongoDB", "type" -> "database",
    "count" -> 1, "info" -> Document("x" -> 203, "y" -> 102))

  def addDocument(doc: Document) = {
    val observable = testCollection.insertOne(doc)
      .subscribe(new Observer[Completed] {
        override def onNext(result: Completed): Unit = println("Inserted")
        override def onError(e: Throwable): Unit = println(s"Failed ${e.getStackTrace}")
        override def onComplete(): Unit = println("Completed")
    })
  }

  def addDocuments(doc: IndexedSeq[Document]) = {
    val observable = testCollection.insertMany(doc)
      .subscribe(new Observer[Completed] {
        override def onNext(result: Completed): Unit = println("Inserted")
        override def onError(e: Throwable): Unit = println(s"Failed ${e.getStackTrace}")
        override def onComplete(): Unit = println("Completed")
      })
  }

  def

  val documents = (1 to 100) map { i: Int => Document("i" -> i) }



  // to keep JVM running, not required in a play application
  Thread.sleep(3000)

}
