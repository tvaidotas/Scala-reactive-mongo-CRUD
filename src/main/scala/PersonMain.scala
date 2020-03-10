import helpers.MongoHelper
import models.Person
import org.mongodb.scala.{MongoClient, MongoCollection, MongoDatabase}
import org.mongodb.scala.bson.codecs.Macros._
import org.mongodb.scala.bson.codecs.DEFAULT_CODEC_REGISTRY
import org.bson.codecs.configuration.CodecRegistries.{fromProviders, fromRegistries}

object PersonMain extends App {

  val codecRegistry = fromRegistries(fromProviders(classOf[Person]), DEFAULT_CODEC_REGISTRY )
  val mongoClient: MongoClient = MongoClient()
  val database: MongoDatabase = mongoClient.getDatabase("people").withCodecRegistry(codecRegistry)
  val collection: MongoCollection[Person] = database.getCollection("people")

  def addDocument(doc: Person): Unit = {
    collection.insertOne(doc)
      .subscribe(MongoHelper.observeInsert)
  }

  //addDocument(Person("name", "surname"))

  Thread.sleep(3000)
  mongoClient.close()

}
