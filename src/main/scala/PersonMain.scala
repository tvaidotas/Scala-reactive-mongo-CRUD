import Main.testCollection
import helpers.MongoHelper
import models.Person
import org.mongodb.scala.{MongoClient, MongoCollection, MongoDatabase}
import org.mongodb.scala.bson.codecs.Macros._
import org.mongodb.scala.bson.codecs.DEFAULT_CODEC_REGISTRY
import org.bson.codecs.configuration.CodecRegistries.{fromProviders, fromRegistries}
import org.mongodb.scala.model.Filters.equal
import org.mongodb.scala.model.Updates.set

import scala.util.{Failure, Success}
import scala.concurrent.ExecutionContext.Implicits.global


object PersonMain extends App {

  val codecRegistry = fromRegistries(fromProviders(classOf[Person]), DEFAULT_CODEC_REGISTRY )
  val mongoClient: MongoClient = MongoClient()
  val database: MongoDatabase = mongoClient.getDatabase("people").withCodecRegistry(codecRegistry)
  val collection: MongoCollection[Person] = database.getCollection("people")

  def addDocument(doc: Person): Unit = {
    collection.insertOne(doc)
      .subscribe(MongoHelper.observeInsert)
  }

  def addDocuments(doc: IndexedSeq[Person]): Unit = {
    collection.insertMany(doc)
      .subscribe(MongoHelper.observeInsert)
  }

  def getAllDocuments = {
    collection.find()
  }

  def getFirstDocument = {
    collection.find().head()
  }

  def deleteById(id: Int): Unit = {
    collection.deleteOne(equal("_id", 1)).headOption().onComplete {
      case Success(_) => println("Completed")
      case Failure(error) => error.printStackTrace()
    }
  }


  def findById(id: Int): Unit = {
    findByIdReturningFuture(id).onComplete {
      case Success(value) => println(s"The value we've been waiting for is: ${value.getOrElse("Item not found")}")
      case Failure(error) => error.printStackTrace()
    }
  }

  def findByIdReturningFuture(id: Int) = {
    collection.find(equal("_id", id)).headOption()
  }

  def updateName(id: Int, newName: String): Unit = {
    collection.updateOne(equal("_id", id), set("name", newName)).headOption().onComplete {
      case Success(value) => println(s"The value has been updated to: $value")
      case Failure(error) => error.printStackTrace()
    }
  }

  //addDocument(Person("name", "surname"))

  Thread.sleep(3000)
  mongoClient.close()

}
