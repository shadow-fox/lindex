package index.reader

import index.entity.{Schema, Triplets}
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, Row}

object TSVReader extends Reader {

  def readTSV(fileName: String): RDD[Row] = {
    val frame: RDD[Row] = sparkSession.read.option("delimiter", "\t").csv(fileName).toDF().rdd
    frame
  }
}
