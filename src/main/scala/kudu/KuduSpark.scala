package kudu

import org.apache.kudu.client.CreateTableOptions
import org.apache.kudu.spark.kudu.{KuduContext, _}
import org.apache.spark.SparkContext
import org.apache.spark.sql._
import org.apache.spark.sql.types._

import scala.collection.JavaConverters._

object KuduSpark {
  def main(args: Array[String]): Unit = {
    val ss: SparkSession = SparkSession.builder().master("local").appName("kudu").getOrCreate()
    val sc: SparkContext = ss.sparkContext
    //构建 KuduContext
    val kc: KuduContext = new KuduContext("hadoop102:7051,hadoop103:7051,hadoop104:7051", sc)

    //createTable(kc)
    //建表

    /*    import ss.implicits._
        val peopleDF: DataFrame = sc.makeRDD(List(People("1","zhangsan",23,"男"),People("2","lisi",24,"男"))).toDF
        insert(peopleDF,"spark_kudu1")*/

    /* val kuduRDD: RDD[Row] = kc.kuduRDD(sc,"spark_kudu1",List("userId","name","age"))
     kuduRDD.foreach(println(_))*/
    read

    def read: Unit = {
      val kuduOptions = Map(
        "kudu.master" -> "hadoop102:7051,hadoop103:7051,hadoop104:7051",
        "kudu.table" -> "spark_kudu1"
      )
      val kudu: DataFrame = ss.read.options(kuduOptions).kudu
      kudu.show()
    }


    def createTable(kuduContext: KuduContext) = {
      val tableName = "spark_kudu1"
      val schema = StructType(
        StructField("userId", StringType, false) ::
          StructField("name", StringType, false) ::
          StructField("age", IntegerType, false) ::
          StructField("sex", StringType, false) :: Nil)
      //1.3 定义表的主键
      val primaryKey = Seq("userId")
      //1.4 定义分区的 schema
      val options = new CreateTableOptions
      //设置分区

      options.addHashPartitions(List("userId").asJava, 6)

      //设置副本
      options.setNumReplicas(1)
      //1.5 创建表
      if (!kuduContext.tableExists(tableName)) {
        kuduContext.createTable(tableName, schema, primaryKey,
          options)
      }
    }

    def insert(df: DataFrame, tableName: String) = {
      kc.insertRows(df, tableName)
    }

  }

}

case class People(userId: String, name: String, age: Int, sex: String)
