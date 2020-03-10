import helpers.MongoHelper
import org.mongodb.scala._
import org.mongodb.scala.bson.ObjectId
import org.mongodb.scala.model.Filters._
import org.mongodb.scala.model.Updates._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

object Main extends App {

  val mongoClient: MongoClient = MongoClient()
  val database: MongoDatabase = mongoClient.getDatabase("test")
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
    testCollection.find.toFuture
  }

  def getFirstDocument = {
    testCollection.find.headOption
  }

  def deleteById(id: ObjectId): Unit = {
    testCollection.deleteOne(equal("_id", id)).toFuture.onComplete {
      case Success(_) => println("Completed")
      case Failure(error) => println(error)
    }
  }


  def findById(id: ObjectId): Unit = {
    findByIdReturningFuture(id).onComplete {
      case Success(value) => println(s"The value we've been waiting for is: ${value.getOrElse("Item not found")}")
      case Failure(error) => println(error)
    }
  }

  def findByIdReturningFuture(id: ObjectId) = {
    testCollection.find(equal("_id", id)).headOption
  }

  def updateName(id: ObjectId, newName: String): Unit = {
    testCollection.updateOne(equal("_id", id), set("name", newName)).headOption.onComplete {
      case Success(value) => println(s"The value has been updated to: $value")
      case Failure(error) => println(error)
    }
  }

  //findByIdReturningFuture(1).map(value => println(value.getOrElse("Item not found")))
  //addDocument(MongoHelper.doc)

  Thread.sleep(3000)
  mongoClient.close()

}
