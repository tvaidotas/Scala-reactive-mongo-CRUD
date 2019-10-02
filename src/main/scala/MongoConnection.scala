import org.mongodb.scala.{MongoClient, MongoCollection, MongoDatabase}

class MongoConnection {

  def getClient(client: String) = {
    MongoClient(client)
  }

  def getDatabase(client: MongoClient, database: String) = {
    client.getDatabase(database)
  }

  def getCollection(database: MongoDatabase, collection: String) = {
    database.getCollection(collection)
  }

}
