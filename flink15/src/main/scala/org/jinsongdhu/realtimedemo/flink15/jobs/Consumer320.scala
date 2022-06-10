package org.jinsongdhu.realtimedemo.flink15.jobs
import org.apache.flink.api.common.eventtime.WatermarkStrategy
import org.apache.flink.api.common.serialization.SimpleStringSchema
import org.apache.flink.connector.kafka.source.KafkaSource
import org.apache.flink.connector.kafka.source.enumerator.initializer.OffsetsInitializer
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer
object Consumer320 {
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

    val dataStream = env.fromSource(source,WatermarkStrategy.noWatermarks(),"Kafka Source")
    dataStream.print()

    env.execute("Flink15")

  }

}
