import reactivemongo.api.MongoDriver

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.concurrent.Await

object Runner {

  val uri = "mongodb://localhost"

  def main(args: Array[String]): Unit = {
    // Create Mongo Driver
    val driver = new MongoDriver

    // Connect to localhost
    val connection = driver.connection(List("localhost"))

    // Connect to mongodb
    //val db = Await.result(connection.database("reactivemongo"), Duration(5000, "millis"))
    val db = Await.result(connection.database("reactivemongo"), Duration(5000, "millis"))

    // Connect to mongodb collection
    val collection = db.collection("persons")
  }


}
