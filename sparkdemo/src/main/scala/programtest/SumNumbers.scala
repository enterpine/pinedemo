package programtest

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.SparkSession
object SumNumbers {
  def main(args: Array[String]): Unit = {
//    val con = new SparkConf()
//    con.setAppName("SummNumbers")
//    con.setMaster("local")
//    val sc = new SparkContext(con)
//
//    val rdd = sc.textFile("/Users/jinsong/IdeaProjects/pinedemo/sparkdemo/src/main/resources/data/data.txt")
//    val a = rdd.map(_.toInt).reduce(_+_)
//    println(a)
//    sc.stop()

    val ss = SparkSession.builder().master("local").appName("ss").getOrCreate()
    val src = ss.read.textFile("/Users/jinsong/IdeaProjects/pinedemo/sparkdemo/src/main/resources/data/data.txt")
    val sum = src.selectExpr("cast(value as  int) as a").agg("a"->"max")
    sum.collect().foreach(println(_))
    ss.stop()
  }
}
