name := "lhindex"

version := "0.1"

scalaVersion := "2.11.12"

libraryDependencies += "com.typesafe" % "config" % "1.3.2"
libraryDependencies += "com.github.pureconfig" %% "pureconfig" % "0.9.2"
val circeVersion = "0.9.3"
libraryDependencies ++= Seq(
  "io.circe" %% "circe-core",
  "io.circe" %% "circe-generic",
  "io.circe" %% "circe-parser"
).map(_ % circeVersion)
libraryDependencies += "io.circe" %% "circe-config" % "0.4.1"

libraryDependencies += "org.apache.spark" %% "spark-core" % "2.3.1"
libraryDependencies += "org.apache.spark" %% "spark-sql" % "2.3.1"


libraryDependencies += "org.apache.flink" %% "flink-scala" % "1.6.0"
libraryDependencies += "org.apache.flink" %% "flink-streaming-scala" % "1.6.0"
libraryDependencies += "org.apache.flink" %% "flink-clients" % "1.6.0"

libraryDependencies += "org.apache.lucene" % "lucene-core" % "7.4.0"
libraryDependencies += "org.apache.solr" % "solr-solrj" % "7.4.0"

libraryDependencies += "com.outr" %% "scribe" % "2.6.0"
libraryDependencies += "log4j" % "log4j" % "1.2.17"