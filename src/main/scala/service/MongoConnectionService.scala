package service

import org.mongodb.scala.{Document, MongoClient, MongoCollection, MongoDatabase}

class MongoConnectionService {

  def getClient(client: String): MongoClient = {
    MongoClient(client)
  }

  def getDatabase(client: MongoClient, database: String): MongoDatabase = {
    client.getDatabase(database)
  }

  def getCollection(database: MongoDatabase, collection: String): MongoCollection[Document] = {
    database.getCollection(collection)
  }

}
