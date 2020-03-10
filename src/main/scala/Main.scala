import helpers.MongoHelper
import org.mongodb.scala._
import org.mongodb.scala.model.Filters._
import org.mongodb.scala.model.Updates._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

object Main extends App {

  // Get connection
  val mongoClient: MongoClient = MongoClient("mongodb://localhost")
  // Get database
  val database: MongoDatabase = mongoClient.getDatabase("test")
  // Get collection
  val testCollection = database.getCollection("test")

  def addDocument(doc: Document): Unit = {
    testCollection.insertOne(doc)
      .subscribe(MongoHelper.observeInsert)
  }

  def addDocuments(doc: IndexedSeq[Document]): Unit = {
    testCollection.insertMany(doc)
      .subscribe(MongoHelper.observeInsert)
  }

  def getAllDocuments = {
    testCollection.find()
  }

  def getFirstDocument = {
    testCollection.find().head()
  }

  def deleteById(id: Int): Unit = {
    testCollection.deleteOne(equal("_id", 1)).headOption().onComplete {
      case Success(value) => println("Completed")
      case Failure(error) => error.printStackTrace()
    }
  }


  def findById(id: Int): Unit = {
    testCollection.find(equal("_id", id)).headOption().onComplete {
      case Success(value) => println(s"The value we've been waiting for is: ${value.getOrElse("Item not found")}")
      case Failure(error) => error.printStackTrace()
    }
  }

  def findByIdReturningFuture(id: Int) = {
    testCollection.find(equal("_id", id)).headOption()
  }

  def updateName(id: Int, newName: String): Unit = {
    testCollection.updateOne(equal("_id", id), set("name", newName)).headOption().onComplete {
      case Success(value) => println(s"The value has been updated to: $value")
      case Failure(error) => error.printStackTrace()
    }
  }

 // findByIdReturningFuture(3).map(value => println(value.getOrElse("Item not found")))

  addDocument(MongoHelper.doc)

  // to keep JVM running, not required in a play application
  Thread.sleep(3000)

  // close connection
  mongoClient.close()

}
