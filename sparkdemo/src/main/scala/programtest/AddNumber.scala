package programtest

import org.apache.spark.sql.{Row, SparkSession}

object AddNumber {
  def main(args: Array[String]): Unit = {
    val ss = SparkSession.builder().master("local").appName("aa").getOrCreate()
    val src = ss.read.csv("/Users/jinsong/IdeaProjects/pinedemo/sparkdemo/src/main/resources/data/Add100.txt")
    println(src.schema.toString())
    src.selectExpr("cast(_c0 as int)+100 as c0","cast(_c1 as int)+100 as c1")
      .show()

  }

}
