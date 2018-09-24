package index

import index.entity.{Schema, Triplets}
import index.reader.{Reader, TSVReader}
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.Row

class TaxonomyIndex extends Reader {

  def mapToSchema(row: Row): Schema = {
    Schema(row.get(0).toString, Triplets(row.get(1).toString, row.get(2).toString, row.get(3).toString))
  }

  def processRows(rows: RDD[Row]): Schema = {
    rows.filter(row => row.get(0) != null).map(row => mapToSchema(row))
  }

  def buildTaxonomyRDDs(): Unit = {
    val yagoSchemaFile = sparkSession.sparkContext.getConf.get("yagoSchema")
    val yagoTransitiveTypeFile = sparkSession.sparkContext.getConf.get("yagoTransitiveType")
    val yagoTypesFile = sparkSession.sparkContext.getConf.get("yagoTypes")
    val yagoTaxonomyFile = sparkSession.sparkContext.getConf.get("yagoTaxonomy")
    val schemaRDD: Schema = processRows(TSVReader.readTSV(yagoSchemaFile))
    val transitiveTypeRDD: Schema = processRows(TSVReader.readTSV(yagoTransitiveTypeFile))
    val typeRDD: Schema = processRows(TSVReader.readTSV(yagoTypesFile))
    val taxonomyRDD: Schema = processRows(TSVReader.readTSV(yagoTaxonomyFile))
  }

  def init(): Unit = {
    buildTaxonomyRDDs()
  }
}
