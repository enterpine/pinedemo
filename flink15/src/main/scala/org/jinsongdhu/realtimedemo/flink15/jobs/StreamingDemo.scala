package org.jinsongdhu.realtimedemo.flink15.jobs
import org.apache.flink.api.common.RuntimeExecutionMode
import org.apache.flink.api.common.eventtime.{SerializableTimestampAssigner, WatermarkStrategy}
import org.apache.flink.api.common.serialization.SimpleStringSchema
import org.apache.flink.streaming.api.windowing.time.Time
import org.apache.flink.api.java.functions.KeySelector
import org.apache.flink.configuration.Configuration
import org.apache.flink.connector.kafka.source.KafkaSource
import org.apache.flink.connector.kafka.source.enumerator.initializer.OffsetsInitializer
import org.apache.flink.streaming.api.environment.CheckpointConfig.ExternalizedCheckpointCleanup
import org.apache.flink.streaming.api.environment.ExecutionCheckpointingOptions
import org.apache.flink.streaming.api.{CheckpointingMode, TimeCharacteristic}
import org.apache.flink.streaming.api.functions.sink.filesystem.StreamingFileSink
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.api.windowing.assigners.{SlidingEventTimeWindows, TumblingEventTimeWindows}
import org.apache.flink.streaming.api.windowing.triggers.Trigger
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer
import org.apache.kafka.clients.consumer.OffsetResetStrategy

import java.text.SimpleDateFormat
import java.time.Duration
import java.util.{Calendar, Date, Properties}
object StreamingDemo {
  def main(args: Array[String]): Unit = {

    //Consist value
    val topic_name = "topic_test"
    val bootstrap_servers ="localhost:9092"

    //Flink Env
    val env = StreamExecutionEnvironment.getExecutionEnvironment

    env.enableCheckpointing(1000)
    // set mode to exactly-once (this is the default)
    env.getCheckpointConfig.setCheckpointingMode(CheckpointingMode.EXACTLY_ONCE)
    // make sure 500 ms of progress happen between checkpoints
    env.getCheckpointConfig.setMinPauseBetweenCheckpoints(500)
    // checkpoints have to complete within one minute, or are discarded
    env.getCheckpointConfig.setCheckpointTimeout(60000)
    // only two consecutive checkpoint failures are tolerated
    env.getCheckpointConfig.setTolerableCheckpointFailureNumber(2)
    // allow only one checkpoint to be in progress at the same time
    env.getCheckpointConfig.setMaxConcurrentCheckpoints(1)
    // enable externalized checkpoints which are retained
    // after job cancellation
    env.getCheckpointConfig.setExternalizedCheckpointCleanup(
      ExternalizedCheckpointCleanup.RETAIN_ON_CANCELLATION)
    // enables the unaligned checkpoints
    env.getCheckpointConfig.enableUnalignedCheckpoints()
    // sets the checkpoint storage where checkpoint snapshots will be written
    env.getCheckpointConfig.setCheckpointStorage("file:///Users/jinsong/var/flink/checkpoint")

//    val config = new Configuration()
//    config.set(ExecutionCheckpointingOptions.ENABLE_CHECKPOINTS_AFTER_TASKS_FINISH, true)
//    env.configure(config)


    //Kafka source builder
    val properties = new Properties()
    properties.put("enable.auto.commit","true")
    val source = KafkaSource.builder[String]()
      .setTopics(topic_name)
      .setBootstrapServers(bootstrap_servers)
      .setGroupId("Consumer320_2206101630")
      .setStartingOffsets(OffsetsInitializer.latest())
      .setProperties(properties)
      .setValueOnlyDeserializer(new SimpleStringSchema())
      .build()

    //Create source stream
  val dataStream = env.fromSource(
    source
    ,WatermarkStrategy.forBoundedOutOfOrderness(Duration.ofSeconds(30)).withTimestampAssigner(
      new SerializableTimestampAssigner[String] {
        override def extractTimestamp(t: String, l: Long): Long = t.split('\001')(4).toLong
      }
    ),"Kafka Source")

    //Processing
    val formatline = dataStream.map(x=>{
      val a = x.split('\001')
      val simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
      var timetext = ""
      val date = new Date()
      date.setTime(System.currentTimeMillis())
      timetext = simpleDateFormat.format(date).toString
      (a(0),a(1),a(2),a(3).toInt,timetext,a(4))
    })
    var keyedstream = formatline.keyBy(new KeySelector[Tuple6[String,String,String,Int,String,String],Tuple3[String,String,String]] {
      override def getKey(in: (String, String, String, Int, String, String)): (String, String, String) ={
        (in._1,in._2,in._3)
      }
    })
      //.window(SlidingEventTimeWindows.of(Time.seconds(20),Time.seconds(5)))
      .sum(3).map(x=>x).print()

//    keyedstream


    env.execute("Flink15")

  }

}
