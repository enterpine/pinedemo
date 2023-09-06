import org.apache.directory.api.asn1.Encoder
import org.apache.spark._
import org.apache.spark.sql.catalyst.encoders.RowEncoder
import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}
import org.apache.spark.sql.{Encoders, Row, SparkSession}
import org.apache.spark.storage.StorageLevel

object TestDf {
  def main(args: Array[String]): Unit = {

    val ss = SparkSession
      .builder()
      .master("local")
      .appName("app")
      .getOrCreate()

    val ds = ss.createDataset( List(
      ("book1", "user1", 0),
      ("book1", "user1", 0),
      ("book1", "user1", 0),
      ("book1", "user1", 0),
      ("book1", "user2", 1),
      ("book3", "user7", 1),
      ("book3", "user8", 0),
      ("book3", "user9", 1),
      ("book3", "user10", 1),
      ("book3", "user1", 0)
    ))(Encoders.tuple(Encoders.STRING,Encoders.STRING,Encoders.scalaInt))
    ds.createOrReplaceTempView("tableDs")


    val st = StructType.apply(List(
      StructField("BookName",StringType,false),
      StructField("UserName",StringType,false),
      StructField("GenderCode",IntegerType,false)
    ))
    val ds_r = ss.createDataset( List(
      Row("book1", "user1", 0),
      Row("book1", "user1", 0),
      Row("book1", "user1", 0),
      Row("book1", "user1", 0),
      Row("book1", "user2", 1),
      Row("book3", "user7", 1),
      Row("book3", "user8", 0),
      Row("book3", "user9", 1),
      Row("book3", "user10", 1),
      Row("book3", "user1", 0)
    ))(RowEncoder.apply(st)).createOrReplaceTempView("tableDs_r")



    val df = ss.createDataFrame( List(
      ("book1", "user1", 0),
      ("book1", "user1", 0),
      ("book1", "user1", 0),
      ("book1", "user1", 0),
      ("book1", "user2", 1),
      ("book3", "user7", 1),
      ("book3", "user8", 0),
      ("book3", "user9", 1),
      ("book3", "user10", 1),
      ("book3", "user1", 0)
    ))

    df.createOrReplaceTempView("tableDf")
    ss.sqlContext.sql(
    """
      |select _1,_2,_3
      |,row_number() over(distribute by _1 order by _2) as rn
      |from tableDs
      |""".stripMargin).show(100)




  }
}
