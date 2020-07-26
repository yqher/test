package test

import org.apache.spark.SparkContext
import org.apache.spark.sql.{DataFrame, SparkSession}

object Test6 {
  def main(args: Array[String]): Unit = {
    //val ss: SparkSession = SparkSession.builder().appName("hive").enableHiveSupport().getOrCreate()
    val ss: SparkSession = SparkSession.builder().master("local").appName("hive").getOrCreate()
    val sc: SparkContext = ss.sparkContext

    import ss.implicits._
    val peopleRdd = sc.makeRDD(List((1, "marry", 34, "male", "20180203"), (2, "jom", 43, "female", "20180212"), (3, "zhangsan", 35, "male", "20190304")
      , (4, "marry", 34, "male", "20200203"), (5, "jom", 43, "female", "20200512"), (6, "zhangsan", 35, "male", "20200602"))
      , 3)
    val peopleDF: DataFrame = peopleRdd.map(sp => {
      (sp._1, sp._2, sp._3, sp._4, sp._5)
    }).toDF("id", "name", "age", "sex", "time")
    peopleDF.show()


    val peopleRdd1 = sc.makeRDD(List((1, "marry", 34, "male", "20180203"), (2, "marry", 34, "male", "20180203"), (3, "marry", 34, "male", "20180203"))
      , 3)
    val peopleDF1: DataFrame = peopleRdd1.map(sp => {
      (sp._1, sp._2, sp._3, sp._4, sp._5)
    }).toDF("id", "name", "age", "sex", "time")
    peopleDF1.show()
    peopleDF.join(peopleDF1, Seq("id"), "left").show()
    /*val a = sc.makeRDD(List((1),(2))).toDF("id")
    a.show()*/
    //    peopleDF1.show()

    /// peopleDF.join(a,Seq("id"),"left").show()
    /* peopleDF.filter("id>=2 and id <=3").show()
     peopleDF.select(" id ").show()*/
    /* val ag = 34
     val name = "marry"
     println(s"--peopleDF-----${peopleDF.rdd.partitions.size}--------")

     val filPeople: Dataset[Row] = peopleDF.repartition(4)
     println(filPeople.rdd.partitions.size)
    // val filPeople:DataFrame = value.filter($"age">=30&&$"age"<=40)
     val filPeople1: Dataset[Row] = filPeople.filter(s"name='${name}'")
     filPeople1.show()
     val frame: DataFrame = filPeople.join(peopleDF,"id")
     println(filPeople.rdd.partitions.size)

     println(s"---frame------${frame.rdd.partitions.size}-----------")
 */

    /*  peopleDF.repartition(5).write.format("parquet").mode("overwrite").saveAsTable("testdata.people")
    peopleDF.show()
    val name1: String = "marry"
    val time1 = "20200601"
    val frame: DataFrame = ss.sql(s"select * from testdata.people ").filter(s"time <= '${time1}'")
    println(s"-----------${frame.count}-------")
    frame.show()
  */
  }
}
