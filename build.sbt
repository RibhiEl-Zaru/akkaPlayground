name := "akkaPlayground"

version := "0.1"

scalaVersion := "2.13.5"

idePackagePrefix := Some("org.rezonation.akka")
val AkkaVersion = "2.6.14"
libraryDependencies +=
  ("com.typesafe.akka" %% "akka-actor" % AkkaVersion)