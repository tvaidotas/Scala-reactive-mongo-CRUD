import org.mongodb.scala._

object Main extends App {

  // Get connection
  val mongoClient: MongoClient = MongoClient("mongodb://localhost")
  // Get database
  val database: MongoDatabase = mongoClient.getDatabase("test")
  // Get collection
  val testCollection = database.getCollection("test")

  // Example document _id has to be unique
  val doc: Document = Document("_id" -> 1, "name" -> "MongoDB", "type" -> "database",
    "count" -> 1, "info" -> Document("x" -> 203, "y" -> 102))
  // Example document _id has to be unique
  val doc1: Document = Document("_id" -> 2, "name" -> "MongoDB", "type" -> "database",
    "count" -> 1, "info" -> Document("x" -> 203, "y" -> 102))
  // Example document _id has to be unique
  val doc2: Document = Document("_id" -> 3, "name" -> "MongoDB", "type" -> "database",
    "count" -> 1, "info" -> Document("x" -> 203, "y" -> 102))
  // Example documents collection _id would be uniques as mongodb would create it itself
  val documents = (1 to 10) map { i: Int => Document("i" -> i) }

  def addDocument(doc: Document) = {
    val observable = testCollection.insertOne(doc)
      .subscribe(new Observer[Completed] {
        override def onNext(result: Completed): Unit = println("Inserted")
        override def onError(e: Throwable): Unit = println(s"Failed ${e.getStackTrace.toString}")
        override def onComplete(): Unit = println("Completed")
    })
  }

  def addDocuments(doc: IndexedSeq[Document]) = {
    val observable = testCollection.insertMany(doc)
      .subscribe(new Observer[Completed] {
        override def onNext(result: Completed): Unit = println("Inserted")
        override def onError(e: Throwable): Unit = println(s"Failed ${e.getStackTrace.toString}")
        override def onComplete(): Unit = println("Completed")
      })
  }

  def getAllDocuments() = {
    testCollection.find()
  }

  def getFirstDocument() = {
    testCollection.find().head()
  }


  //addDocument(doc)
  //addDocuments(documents)
  //addDocuments(IndexedSeq[Document](doc, doc1, doc2))

  //getFirstDocument().map(println(_))

  // to keep JVM running, not required in a play application
  Thread.sleep(3000)

}
