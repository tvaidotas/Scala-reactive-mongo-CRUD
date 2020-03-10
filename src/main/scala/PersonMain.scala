import models.Person
import org.mongodb.scala.{Completed, MongoClient, MongoCollection, MongoDatabase, Observer}
import org.mongodb.scala.bson.codecs.Macros._
import org.mongodb.scala.bson.codecs.DEFAULT_CODEC_REGISTRY
import org.bson.codecs.configuration.CodecRegistries.{fromRegistries, fromProviders}

object PersonMain extends App {

  val codecRegistry = fromRegistries(fromProviders(classOf[Person]), DEFAULT_CODEC_REGISTRY )
  val mongoClient: MongoClient = MongoClient("mongodb://localhost")
  val database: MongoDatabase = mongoClient.getDatabase("mydb").withCodecRegistry(codecRegistry)
  val collection: MongoCollection[Person] = database.getCollection("test")

  def addDocument(doc: Person) = {
    collection.insertOne(doc)
      .subscribe(new Observer[Completed] {
        override def onNext(result: Completed): Unit = println(s"Inserted $doc")
        override def onError(e: Throwable): Unit = println(s"Failed $e")
        override def onComplete(): Unit = println(s"Completed inserting $doc")
      })
  }

  addDocument(Person("name", "surname"))

  mongoClient.close()

}
