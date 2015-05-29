name := "spark-test"

version := "0.1"

scalaVersion := "2.10.5"

libraryDependencies ++= Seq(
  "com.google.jimfs" % "jimfs" % "1.0",
  "org.specs2" %% "specs2-core" % "3.6",
  "org.apache.spark" %% "spark-core" % "1.3.0" % "optional")