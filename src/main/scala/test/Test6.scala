package test

import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkContext
import org.apache.spark.sql.functions.{col, _}
import org.apache.spark.sql.{Row, _}

import scala.collection.mutable.ArrayBuffer
import scala.collection.{GenTraversableOnce, mutable}

object Test6 {
  def main(args: Array[String]): Unit = {
    Logger.getLogger("org").setLevel(Level.OFF)
    //val ss: SparkSession = SparkSession.builder().appName("hive").enableHiveSupport().getOrCreate()
    val ss: SparkSession = SparkSession.builder().master("local").appName("hive").getOrCreate()
    val sc: SparkContext = ss.sparkContext

    import ss.implicits._
    /*  val peopleRdd = sc.makeRDD(List((1, "marry", 10, "male", "20180203"), (2, "jom", 20, "female", "20180212"), (3, "zhangsan", 35, "male", "20190304")
        , (4, "marry", 34, "male", "20200203"), (5, "jom", 43, "female", "20200512"), (6, "zhangsan", 35, "male", "20200602"))
        , 3)
      val peopleDF: DataFrame = peopleRdd.map(sp => {
        (sp._1, sp._2, sp._3, sp._4, sp._5)
      }).toDF("id", "name", "age", "sex", "time")
      peopleDF.show()*/
    val peopleRdd = sc.makeRDD(List((1, "marry", 10.4, "2020-08-01 19:34:45", 20.5, "2020-08-02 19:34:45", 45.6, "2020-08-03 19:34:45"), (2, "jom", 20.4, "2020-08-01 14:34:45", 435.6, "2020-08-02 14:34:45", 46.6, "2020-08-03 14:34:45"), (3, "zhangsan", 35.4, "2020-08-01 13:34:45", 46.46, "2020-08-02 13:34:45", 65.4, "2020-08-03 13:34:45")
      , (4, "marry", 34.0, "2020-08-01 15:34:45", 44.45, "2020-08-02 18:34:45", 46.5, "2020-08-03 19:34:44"))
      , 3)

    val peopleDF: DataFrame = peopleRdd.map(sp => {
      (sp._1, sp._2, sp._3, sp._4, sp._5, sp._6, sp._7, sp._8)
    }).toDF("id", "name", "P1", "P1_TIME", "P2", "P2_TIME", "P3", "P3_TIME")
    peopleDF.show()
    peopleDF.printSchema()

    // peopleDF.dropDuplicates("name").show
    // peopleDF.agg(max("P1_TIME")).show
    peopleDF.withColumn("bs", when($"name".endsWith("m") === lit("false"), 0).otherwise(2))
      .explain()

    peopleDF.dtypes.foreach(println(_))

    //  peopleDF.filter($"P1"===s"${peopleDF.agg(max("P1_TIME")).toString()}").show
    /*
        //求每行最大最小P以及对应的时间
        val result: DataFrame = peopleDF.map(row => {
          val ID = row.getAs[Int]("id")
          val NAME: String = row.getAs[String]("name")
          var MAXP: Double = row.getAs[Double]("P1")
          var MAXPTIME: String = row.getAs[String]("P1_TIME")
          var MINP: Double = row.getAs[Double]("P1")
          var MINPTIME: String = row.getAs[String]("P1_TIME")
          for (i <- 2 to 3) {
            val P: Double = row.getAs[Double](s"P${i.toString}")
            if (P > MAXP) {
              MAXP = P
              MAXPTIME = row.getAs[String](s"P${i.toString}_TIME")
            }
            if (P < MINP) {
              MINP = P
              MINPTIME = row.getAs[String](s"P${i.toString}_TIME")
            }
          }
          (ID, NAME, MAXP, MAXPTIME, MINP, MINPTIME)
        }
        ).toDF("ID", "NAME", "MAX_P", "MAX_P_TIME", "MIN_P", "MIN_P_TIME")
        result.show()
        result.printSchema()*/

    /* //遍历每列求最大最小
      val result: Dataset[(Int, String, Double, Double)] = peopleDF.map(row => {
        val x1 = row.getAs[Int]("id")
        val x2 = row.getAs[String]("name")
        var max: Double = row.getAs[Double]("P1")
        var min:Double = row.getAs[Double]("P1")
        for (i <- 2 to 3) {
          val num: String = i.toString
          var value: Double = row.getAs[Double](s"P$num")
          if (max < value)
            max = value
          if(min> value)
            min = value
        }
        (x1, x2, max,min)

      })

      result.toDF("ID","NAME","MAX_P","MIN_P")show()*/

    /*  //列转行
       val rs: Dataset[ArrayBuffer[B11]] = peopleDF.map(r=>getRow(r))
       val result213: Dataset[B11] = rs.flatMap(x=>x)
       result213.show()
   */


    //peopleDF.select(col("id")as("id12")).show()
    /* val map: mutable.Map[String, String] = scala.collection.mutable.Map[String,String]()
    map +=("id"->"id1","age"->"age1")
    val frame: DataFrame = columnNamesRename(peopleDF,map)
    frame.show()*/
    /* peopleDF.map(rows=>{
      if(rows.get(1).equals("marry")){
        rows.get(1) =null

      }

     })*/
    /* //注册成表
     peopleDF.createOrReplaceTempView("people")
     ss.sql("select sex,age,sum(age) over(partition by sex order by age rows between unbounded preceding and current row) as sum from people").show()
 /*    peopleDF.columns.foreach(println(_))
     peopleDF.dtypes.foreach(println(_))*/
     ss.sql("select sex,age from people"). groupBy("sex").agg(collect_set("age") as("age")).select($"sex",explode($"age") as("age")).show()*/

    /*    val addFunction: UserDefinedFunction = ss.udf.register("add",(x:Int) => x + 1)
        peopleDF.withColumn("age+1",addFunction($"age")).show()*/
    /*val comper: UserDefinedFunction = ss.udf.register("comp",comparess _)

    peopleDF.withColumn("compare",comper($"age")).show()*/

    /* val peopleRdd1 = sc.makeRDD(List((1, "marry", 34, "male", "20180203"), (2, "marry", 34, "male", "20180203"), (3, "marry", 34, "male", "20180203"))
       , 3)
     val peopleDF1: DataFrame = peopleRdd1.map(sp => {
       (sp._1, sp._2, sp._3, sp._4, sp._5)
     }).toDF("id", "name", "age", "sex", "time")
     peopleDF1.show()
     peopleDF.join(peopleDF1, Seq("id"), "left").show()*/
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

  def getRow(row: Row): ArrayBuffer[B11] = {
    val array = new ArrayBuffer[B11]()
    val ID = row.getAs[Int]("id")

    val NAME = row.getAs[String]("name")
    //println(ID,NAME)
    for (i <- 1 to 3) {
      var P = row.getAs[Double](s"P${i.toString}")
      array.append(new B11(ID, NAME, P))
      array
      //   println(P)
    }
    //array.foreach(println(_))
    array
  }

  def getRow1: (Row) => GenTraversableOnce[B11] = { t =>

    val array = new Array[B11](3)
    val ID = t.getAs[Int]("id")

    val NAME = t.getAs[String]("name")
    //println(ID,NAME)
    for (i <- 1 to 3) {
      var P = t.getAs[Double](s"P${i.toString}")
      array.updated(i - 1, new B11(ID, NAME, P))
      //   println(P)
    }
    array
  }

  def columnNamesRename(df: DataFrame, map: mutable.Map[String, String]) = {
    val oldColumnNames: Seq[String] = df.columns.toSeq
    val renamedColumns: Seq[Column] = oldColumnNames.map(name => {
      val newName = map.get(name).getOrElse(name)
      col(name).as(newName)
    })
    df.select(renamedColumns: _*)
  }

  def comparess(age: Int): Int = {
    if (age > 35)
      1
    else
      0
  }
}

case class B11(val ID: Int,
               val NAME: String,
               val MAXP: Double) extends Product with Serializable {
  override def productElement(n: Int): Any = n match {
    case 0 => ID
    case 1 => NAME
    case 2 => MAXP
  }

  override def productArity: Int = 3

  override def canEqual(that: Any): Boolean = that.isInstanceOf[B11]

}



