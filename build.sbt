name := "akkaPlayground"

version := "0.1"

scalaVersion := "2.13.5"

idePackagePrefix := Some("rezonation.akka")
val AkkaVersion = "2.6.14"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % AkkaVersion,
  "com.typesafe.scala-logging" %% "scala-logging" % "3.9.3"
)