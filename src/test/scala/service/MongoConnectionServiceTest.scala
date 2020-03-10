package service

import helpers.BaseTest
import org.mockito.Mockito._
import org.mongodb.scala.{Document, MongoClient, MongoCollection, MongoDatabase}

class MongoConnectionServiceTest extends BaseTest {

    var mongoConnection: MongoConnectionService = _
    var mockMongoConnection: MongoConnectionService = _

  before {
    mongoConnection = new MongoConnectionService
    mockMongoConnection = mock[MongoConnectionService]
  }

  "Trying to get mongo client" should "produce client" in {
      when(mockMongoConnection.getClient("")).thenReturn(MongoClient("mongodb://blah"))
      assert(mongoConnection.getClient("mongodb://blah").isInstanceOf[MongoClient])
    }

  "Trying to get a mongo database" should "produce database" in {
    val mongoClient = mock[MongoClient]
    val mongoDatabase = mock[MongoDatabase]
    when(mockMongoConnection.getDatabase(mongoClient,"people")).thenReturn(mongoDatabase)
    when(mongoClient.getDatabase("people")).thenReturn(mongoDatabase)
    assert(mongoConnection.getDatabase(mongoClient,"people").isInstanceOf[MongoDatabase])
  }

  "Trying to get a people collection" should "produce the collection" in {
    val mongoDatabase = mock[MongoDatabase]
    val mongoCollection = mock[MongoCollection[Document]]
    when(mockMongoConnection.getCollection(mongoDatabase,"people")).thenReturn(mongoCollection)
    mongoConnection.getCollection(mongoDatabase,"people")
  }

}
