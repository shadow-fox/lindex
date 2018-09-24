package index.config

import java.util
import com.typesafe.config.{ ConfigFactory, ConfigMemorySize }
import io.circe.generic.auto._
import io.circe.config.syntax._
import scala.concurrent.duration.FiniteDuration


import com.typesafe.config.{ConfigFactory, ConfigValue}

case class Datasets (
                      yago: Yago
                    )

case class AppProperties (
                           datasets: Seq[Datasets],
                           stream: Seq[Stream]
                         )

case class Yago (
                  schema: String,
                  transitiveType: String,
                  types: String,
                  taxonomy: String,
                  path: String
                )

case class Spark (
                   name: String,
                   url: String,
                   memory: String,
                   memoryFraction: String,
                   storageFraction: String
                 )

case class Stream (
                    spark: Spark
                  )

class AppConfig {
  def getConfig: AppProperties =  {
    val config = ConfigFactory.load()
    val value = config.as[AppProperties]
    value.right.get
  }
}
