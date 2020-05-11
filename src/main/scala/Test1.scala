import java.util.Date

object Test1 {
  def main(args: Array[String]): Unit = {

    import java.util.Calendar
    //1.实例化
    //方式一：创建其子类（GregorianCalendar的对象
    //方式二：调用其静态方法getInstance()
    val calendar: Calendar = Calendar.getInstance
    //        System.out.println(calendar.getClass());

    //2.常用方法
    //get()
    val days: Int = calendar.get(Calendar.DAY_OF_MONTH)
    System.out.println(days)
    System.out.println(calendar.get(Calendar.DAY_OF_YEAR))

    import java.util.Calendar
    //set()
    //calendar可变性
    calendar.set(Calendar.DAY_OF_MONTH, 22)
    val days1: Int = calendar.get(Calendar.DAY_OF_MONTH)
    System.out.println(days1)


    import java.util.Calendar
    //add()
    calendar.add(Calendar.DAY_OF_MONTH, -3)
    val days2: Int = calendar.get(Calendar.DAY_OF_MONTH)
    System.out.println(days2)
    println(calendar)


    //getTime():日历类---> Date//getTime():日历类---> Date

    val date: Date = calendar.getTime()
    println(date)
    /*  val dateDt = new Date()
      val cal = Calendar.getInstance
      cal.setTime(dateDt)
      cal.add(Calendar.DATE, -1)

      def calcDt(time: Long) = (time + 8 * 1000 * 3600) / (3600 * 1000 * 24) % 2
      val rDataMpDt = calcDt(cal.getTime.getTime)
      println("rDataMpDt>>>>>>>>rDataMpDt>>>>>>>>>>>>>rDataMpDt>>>>>>>>>>>>" + rDataMpDt)*/

    //        val conf: SparkConf = new SparkConf().setMaster("local").setAppName("spark-test")
    //        val sc = new SparkContext(conf)
    //        val linesRDD: RDD[String] = sc.textFile(args(0))
    //        val wordsRDD: RDD[String] = linesRDD.flatMap(line => line.split(" "))
    //        val counts: RDD[(String, Int)] = wordsRDD.map(word=>(word,1)).reduceByKey(_+_)
    //         counts.saveAsTextFile(args(1))
    //val date = LocalDate now()
    //    println("LocalDate now() >>>>>>>>>>>>" + date)
    //
    //    val strDate = DateTimeFormatter.BASIC_ISO_DATE.format(date.minusDays(3))
    //
    //    val lastMonthDate = DateTimeFormatter.BASIC_ISO_DATE.format(date.minusMonths(1)).take(6)
    //
    //    val lastFourWeekDate = DateTimeFormatter.BASIC_ISO_DATE.format(date.minusDays(3).minusWeeks(4))
    //
    //    val lastDate = DateTimeFormatter.BASIC_ISO_DATE.format(date.minusDays(4))
    //    println("strDate"+strDate)
    //    println("lastMonthDate"+lastMonthDate)
    //    println("lastFourWeekDate"+lastFourWeekDate)
    //    println("lastDate"+lastDate)


  }
}
