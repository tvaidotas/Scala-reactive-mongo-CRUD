import helpers.BaseTest
import org.mongodb.scala.MongoClient
import service.MongoConnectionService
import org.mockito.Mockito._

class MongoConnectionTest extends BaseTest {

  "Trying to get mongo client" should "produce client" in {
      val mongoConnection = mock[MongoConnectionService]
      when(mongoConnection.getClient("")).thenReturn(MongoClient("mongodb://blah"))
      val realMongoConnection = new service.MongoConnectionService
      assert(realMongoConnection.getClient("mongodb://blah").isInstanceOf[MongoClient])
    }

//  test("Should get a Database") {
//    val mongoConnection = mock[service.MongoConnection]
//    val mongoClient = mock[MongoClient]
//    val mongoDatabase = mock[MongoDatabase]
//    when(mongoConnection.getDatabase(mongoClient,"")).thenReturn(mongoDatabase)
//    val realMongoConnection = new service.MongoConnection
//    realMongoConnection.getDatabase(mongoClient,"")
////    assert(realMongoConnection.getDatabase(mongoClient,"").getClass.toString.isEmpty)
//  }
//
//  test("should get a Collection") {
//    val mongoConnection = mock[service.MongoConnection]
//    val mongoClient = mock[MongoClient]
//    val mongoDatabase = mock[MongoDatabase]
//    val mongoCollection = mock[MongoCollection[Document]]
//    when(mongoConnection.getCollection(mongoDatabase,"")).thenReturn(mongoCollection)
//    val realMongoConnection = new service.MongoConnection
//    realMongoConnection.getCollection(mongoDatabase,"")
//  }

}
