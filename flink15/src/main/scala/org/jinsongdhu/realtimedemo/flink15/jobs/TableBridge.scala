package org.jinsongdhu.realtimedemo.flink15.jobs

import org.apache.flink.api.common.serialization.SimpleStringSchema
import org.apache.flink.connector.kafka.source.KafkaSource
import org.apache.flink.connector.kafka.source.enumerator.initializer.OffsetsInitializer
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.table.sources.wmstrategies.WatermarkStrategy

object TableBridge {
  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.getExecutionEnvironment

    val topic_name = "topic_test"
    val bootstrap_servers ="localhost:9092"

    val source = KafkaSource.builder[String]()
      .setTopics(topic_name)
      .setBootstrapServers(bootstrap_servers)
      .setGroupId("Consumer320_2206101630")
      .setStartingOffsets(OffsetsInitializer.earliest())
      .setValueOnlyDeserializer(new SimpleStringSchema())
      .build()

//    env.fromSource(source,WatermarkStrategy)

  }

}
