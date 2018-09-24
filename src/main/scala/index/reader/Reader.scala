package index.reader

import index.config.{AppConfig, StreamContext}
import org.apache.spark.sql.SparkSession

trait Reader {
  val sparkSession: SparkSession = new StreamContext().createSparkSession
}
