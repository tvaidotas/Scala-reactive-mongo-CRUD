//import org.mongodb.scala.{Document, MongoClient, MongoCollection, MongoDatabase}
//import org.scalatest.{BeforeAndAfter, FunSuite, Inside, Inspectors, Matchers, OptionValues}
//import org.mockito.Mockito._
//import org.scalatest._
//import org.scalatestplus.mockito.MockitoSugar
//
//class MongoConnectionTest extends FunSuite with Matchers
//with OptionValues with Inside with Inspectors with MockitoSugar with BeforeAndAfter {
//
//
//  test("Should get a MongoClient") {
//    val mongoConnection = mock[service.MongoConnection]
//    when(mongoConnection.getClient("")).thenReturn(MongoClient("mongodb://blah"))
//    val realMongoConnection = new service.MongoConnection
//    assert(realMongoConnection.getClient("mongodb://blah").isInstanceOf[MongoClient])
//  }
//
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
//
//}
