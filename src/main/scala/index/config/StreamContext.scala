package index.config

import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.SparkSession

class StreamContext {

  def createSparkConf(): SparkConf = {
    val sparkConf = new SparkConf()
    val config = new AppConfig().getConfig
    for (elem <- config.datasets) {
      sparkConf.set("yagoSchema", elem.yago.path ++ elem.yago.schema)
      sparkConf.set("yagoTransitiveType", elem.yago.path ++ elem.yago.transitiveType)
      sparkConf.set("yagoTypes", elem.yago.path ++ elem.yago.types)
      sparkConf.set("yagoTaxonomy", elem.yago.path ++ elem.yago.taxonomy)
    }
    for (elem  <- config.stream) {
      sparkConf
        .setMaster(elem.spark.url)
        .setAppName(elem.spark.name)
        .set("spark.driver.memory", elem.spark.memory)
        .set("spark.executor.memory", elem.spark.memory)
        .set("spark.memory.fraction", elem.spark.memoryFraction)
        .set("spark.memory.storageFraction", elem.spark.storageFraction)
    }
    sparkConf
  }

  def createSparkSession: SparkSession = {
    val sparkSession = SparkSession.builder().appName("KB Cluster").config(createSparkConf()).getOrCreate()
    sparkSession
  }

  def createFlinkEnv(): Unit = {
    val env = StreamExecutionEnvironment.getExecutionEnvironment
  }
}
