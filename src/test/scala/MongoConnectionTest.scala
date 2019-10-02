import org.mongodb.scala.MongoClient
import org.scalatest.{BeforeAndAfter, FunSuite, Inside, Inspectors, Matchers, OptionValues}
import org.mockito.Mockito._
import org.scalatest._
import org.scalatestplus.mockito.MockitoSugar

class MongoConnectionTest extends FunSuite with Matchers
with OptionValues with Inside with Inspectors with MockitoSugar with BeforeAndAfter {


  test("Should get a MongoClient") {
    val mongoConnection = mock[MongoConnection]
    when(mongoConnection.getClient("")).thenReturn(MongoClient("mongodb://blah"))
    val realMongoConnection = new MongoConnection
    assert(realMongoConnection.getClient("mongodb://blah").isInstanceOf[MongoClient])
  }

}
