package org.jinsongdhu.realtimedemo.flink15.jobs

import org.apache.flink.api.common.eventtime.WatermarkStrategy
import org.apache.flink.api.common.serialization.SimpleStringSchema
import org.apache.flink.connector.kafka.source.KafkaSource
import org.apache.flink.connector.kafka.source.enumerator.initializer.OffsetsInitializer
import org.apache.flink.streaming.api.scala._

import org.apache.flink.api.scala._
import org.apache.flink.table.api._

object TableDemo {
  def main(args: Array[String]): Unit = {

    val setting =
      EnvironmentSettings
      .newInstance()
      .inStreamingMode()
      .build()

    val tEnv = TableEnvironment.create(setting)

    tEnv.fromValues(
      row(1,"ABC"),
      row(2L,"ABCD")
    )

  }

}
