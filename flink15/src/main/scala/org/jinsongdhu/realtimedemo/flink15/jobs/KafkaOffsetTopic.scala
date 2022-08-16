package org.jinsongdhu.realtimedemo.flink15.jobs


import org.apache.flink.api.common.eventtime.{SerializableTimestampAssigner, WatermarkStrategy}
import org.apache.flink.api.common.serialization.SimpleStringSchema
import org.apache.flink.api.java.functions.KeySelector
import org.apache.flink.connector.kafka.source.KafkaSource
import org.apache.flink.connector.kafka.source.enumerator.initializer.OffsetsInitializer
import org.apache.flink.streaming.api.scala._

import java.text.SimpleDateFormat
import java.time.Duration
import java.util.{Date, Properties}
//import org.apache.kafka.common.

object KafkaOffsetTopic {
  // bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic __consumer_offsets --from-beginning
  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.getExecutionEnvironment

    val topic_name = "__consumer_offsets"
    val bootstrap_servers ="localhost:9092"

    val properties = new Properties()
//    properties.put("enable.auto.commit","true")
//    properties.put("key.deserializer","org.apache.kafka.common.serialization.StringDeserializer")
//    properties.put("value.deserializer","org.apache.kafka.common.serialization.StringDeserializer")
    properties.put("formatter","kafka.coordinator.group.GroupMetadataManager$OffsetsMessageFormatter")

    val source = KafkaSource.builder[String]()
      .setTopics(topic_name)
      .setBootstrapServers(bootstrap_servers)
      .setGroupId("Consumer320_2206101630_offsetsTopic")
      .setStartingOffsets(OffsetsInitializer.latest())
      .setProperties(properties)
      .setValueOnlyDeserializer(new SimpleStringSchema())
      .build()



    //eventtime在1分钟前的话，不会处理
val dataStream = env.fromSource(source,WatermarkStrategy.noWatermarks(),"KafkaOffsets Source")

    dataStream.map(x=>x)print()





    env.execute("Flink15")

  }

}
