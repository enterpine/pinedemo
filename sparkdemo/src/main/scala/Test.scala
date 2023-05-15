import org.apache.spark._

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
        ("book1", "user2", 1),
        ("book2", "user3", 0),
        ("book2", "user4", 1),
        ("book2", "user5", 0),
        ("book2", "user6", 0),
        ("book3", "user7", 1),
        ("book3", "user8", 1),
        ("book3", "user9", 1),
        ("book3", "user10", 0),
        ("book4", "user2", 1)
      )
    )
    val a = rdd
      .map(x=>((x._1,x._2,x._3),1))
      .reduceByKey(_+_)
      .map(x=>{
        if(x._1._3==0){
          (x._1._1,(1,0))
        }else{
          (x._1._1,(0,1))
        }
      }).reduceByKey((x,y)=>{
      (x._1+y._1,x._2+y._2)
    })
    a.collect().foreach(x=>{
      println(x)
    })


  }
}
