import org.apache.spark._
import org.apache.spark.storage.StorageLevel

object Test {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
    conf.setMaster("local")
    conf.setAppName("app")
    val sc =new SparkContext(conf)
    val rdd = sc.makeRDD(
      List(
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
      )
    )

    val rdd1 = rdd.map(x=>((x._1,x._2,x._3),0)).reduceByKey(_+_).map(_._1)
    val a1 = rdd1.map(x=>((x._1),x._3))
      .aggregateByKey((0,0))((a,b)=>{
        if(b==0){
          (a._1+1,a._2)
        }else{
          (a._1,a._2+1)
        }
      },(c,d)=>{
        (c._1+d._1,c._2+d._2)
      })

    val a2 = rdd.map(x=>(x._1,x._2))
      .sortByKey(false)
      .sortBy[(String,Int)](x=>{(x._1,x._2.replace("user","").toInt)},false)

    val a3 = rdd.repartition(10)
    a2.collect().foreach(x=>{
      println(x)
    })


  }
}
