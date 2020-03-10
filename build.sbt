name := "ReactiveMongoCRUD"

version := "0.1"

scalaVersion := "2.13.1"

libraryDependencies ++= Seq(
  "org.slf4j" % "slf4j-log4j12" % "1.7.25",
  "org.mongodb.scala" %% "mongo-scala-driver" % "2.8.0",
  "org.scalatest" %% "scalatest" % "3.0.8" % Test,
  "org.mockito" % "mockito-core" % "3.3.0" % Test
)
