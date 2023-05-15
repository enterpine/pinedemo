package programtest

import org.apache.spark.sql.SparkSession
import org.apache.spark.{SparkConf, SparkContext}

object CntWords {
  def main(args: Array[String]): Unit = {
//    val conf = new SparkConf()
//    conf.setMaster("local").setAppName("cw")
//    val sc = new SparkContext(conf)
//    val src = sc.textFile("/Users/jinsong/IdeaProjects/pinedemo/sparkdemo/src/main/resources/data/CntWords.txt")
//    val cntr = src.map((_,1)).reduceByKey(_+_)
//    cntr.sortBy(_._2,false).foreach(println(_))

    val ss = SparkSession.builder().master("local").appName("cw").getOrCreate()
    val src = ss.read.textFile("/Users/jinsong/IdeaProjects/pinedemo/sparkdemo/src/main/resources/data/CntWords.txt")
      .createOrReplaceTempView("mytable")
    val result = ss.sql(
      """
        |select value,count(0) as cnt
        |from mytable
        |group by value order by 2 desc
        |""".stripMargin)
    result.show()
  }
}
