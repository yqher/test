import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.functions.expr
import org.apache.spark.sql.types.DecimalType
import org.apache.spark.sql.{Column, DataFrame, SparkSession}

object Test2 {
  def main(args: Array[String]): Unit = {
    val spark: SparkSession = SparkSession.builder().master("local[*]").appName("spark-test").getOrCreate()
    import spark.implicits._
    val sc: SparkContext = spark.sparkContext
    val peopleRDD: RDD[String] = sc.textFile("people.txt")
    val PeopleConRDD: RDD[(String, Int)] = peopleRDD.map(line => {
      val sp: Array[String] = line.split(" ")
      (sp(0), sp(1).trim.toInt)
    })
    val peopleDF: DataFrame = PeopleConRDD.toDF("name", "age")
    println(peopleDF.show(2))
    val joinFrame: DataFrame = peopleDF.join(peopleDF, Seq("name"), "left")
    println(joinFrame.show)

    val colFD: DataFrame = peopleDF.withColumn("new_age", expr("age+100").cast(DecimalType(4, 1)))
    println(colFD.show)
    import org.apache.spark.sql.functions._
    val groupFrame: DataFrame = colFD.groupBy("name").agg(max("age") as "max_age", avg("age") as "avg_age", countDistinct("age") as "distinct")
    println(groupFrame.show(false))
    /* println(groupFrame.filter($"max_age"<40).select(when($"max_age">40&&$"name"==="jom","old").otherwise("young") as "level",$"name",$"max_age").show(false))
   println(groupFrame.drop("distinct").withColumnRenamed("name","pname").show)*/
    /*   val dateDtLastD = new Date()
       val calLastD = Calendar.getInstance
       calLastD.setTime(dateDtLastD)
       calLastD.add(Calendar.DATE, -4)
       println(calLastD.get(5))*/

    val column: Column = groupFrame.col("name")
    println(peopleDF.union(peopleDF).filter($"age" > 43).show)
    //    println(column)
    //         val column: Column = concat(col("table"),lit("0.5"))
    peopleDF.createOrReplaceTempView("people")
    val frame: DataFrame = spark.sql(s"select * from people where age = 43 ").as("p")
    frame.show

    /* val frame1: DataFrame = spark.sql("select * from p")
     frame1.show()*/


    /*
          //RDD to DF case class

        val peopleDF1: DataFrame = peopleRDD.map(line => {
          val splits: Array[String] = line.split(" ")
          People(splits(0), splits(1).trim.toInt)
        }).toDF
        println(peopleDF1.show(2,false))

        peopleDF.foreach(line => {
            val name: String = line.getAs[String]("name")
          val age: Int = line.getAs[Int]("age")
          println(name+","+age)

        }

        )*/


  }

}

case class People(name: String, age: Int)
