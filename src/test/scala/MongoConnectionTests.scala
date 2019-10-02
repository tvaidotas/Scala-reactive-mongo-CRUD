import org.mongodb.scala
import org.mockito.Mockito
import org.mongodb.scala.{Document, MongoClient, MongoCollection, MongoDatabase}
import org.scalatest.{BeforeAndAfter, FunSuite, Inside, Inspectors, Matchers, OptionValues}
import org.scalatestplus.mockito.MockitoSugar
import org.mockito.Mockito._


class MongoConnectionTests extends FunSuite with Matchers
with OptionValues with Inside with Inspectors with MockitoSugar with BeforeAndAfter {

  var mongoConnection: MongoConnection = _
  var mockMongoConnection: MongoConnection = _

    before {
      mongoConnection = new MongoConnection
      mockMongoConnection = mock[MongoConnection]
    }

  test ("The function getConnection") {
    when(mockMongoConnection.getClient("")).thenReturn(MongoClient("mongodb://test"))
    assert(mockMongoConnection.getClient("").isInstanceOf[MongoClient])
  }

  test ("The function database") {
    val mockDatabase: MongoDatabase = mock[MongoDatabase]
    when(mockMongoConnection.getDatabase()).thenReturn(MongoDatabase(mockDatabase))
    assert(mockMongoConnection.getDatabase().isInstanceOf[MongoDatabase])
  }

  "The function getCollection" should "return a collection" in {
    val mockCollection: com.mongodb.async.client.MongoCollection[Document] = mock[com.mongodb.async.client.MongoCollection[Document]]
    //val test: MongoCollection[Document] = mock[MongoCollection[Document]]

    when(mockMongoConnection.getCollection("test")).thenReturn(new MongoCollection[Document](mockCollection))
    assert(mockMongoConnection.getCollection("test").isInstanceOf[MongoCollection[Document]])
  }






}
