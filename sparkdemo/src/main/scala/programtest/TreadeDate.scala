package programtest

import org.apache.spark.sql.types.{DoubleType, IntegerType, StringType, StructField, StructType}
import org.apache.spark.sql.{Row, SparkSession}
import org.apache.spark.{SparkConf, SparkContext}

object TreadeDate {
  def main(args: Array[String]): Unit = {
//    val conf = new SparkConf()
//    conf.setMaster("local")
//    conf.setAppName("td")
//    val sc = new SparkContext(conf)
//    val src = sc.textFile("/Users/jinsong/IdeaProjects/pinedemo/sparkdemo/src/main/resources/data/TreadeData.txt")
//    val result = src.map(x=>{
//      val s = x.split(',')
//      (s(1),(s(2).toDouble,1))
//    }).reduceByKey((a,b)=>{
//      (a._1+b._1,a._2+b._2)
//    }).sortBy(_._2._1,false)
//      .map(x=>{(x._1,x._2._1,x._2._2)})
//    result.collect().foreach(println)
//    sc.stop()

    val ss = SparkSession.builder().master("local").appName("td").getOrCreate()
    val src = ss.read.textFile("/Users/jinsong/IdeaProjects/pinedemo/sparkdemo/src/main/resources/data/TreadeData.txt")


    val rowRdd = src.toJavaRDD.rdd.map(x=>{
      val a = x.split(',')
      Row(a(0),a(1),a(2).toDouble)
    })

    // 定义Schema
    val schema = StructType(Seq(
      StructField("time", StringType),
      StructField("name", StringType),
      StructField("cash", DoubleType)
    ))

    ss.createDataFrame(rowRdd,schema).createOrReplaceTempView("mytable")


    val r = ss.sql(
      """
        |select name,sum(cash) as cash,count(0) as cnt
        |from mytable
        |group by name order by 2 desc
        |""".stripMargin)
    r.show()
  }

}
