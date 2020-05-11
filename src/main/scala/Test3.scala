import java.text.SimpleDateFormat
import java.util.Date

object Test3 {
  def main(args: Array[String]): Unit = {
    /*  val date = LocalDate now()
      println("LocalDate now() >>>>>>>>>>>>" + date)

      val strDate = DateTimeFormatter.BASIC_ISO_DATE.format(date.minusDays(3))
      println("strDate>>>>>>>>>>"+strDate)

      val lastMonthDate = DateTimeFormatter.BASIC_ISO_DATE.format(date.minusMonths(1)).take(6)
      println("lastMonthDate>>>>>>>>>>"+lastMonthDate)


      val lastFourWeekDate = DateTimeFormatter.BASIC_ISO_DATE.format(date.minusDays(3).minusWeeks(4))
      println("lastFourWeekDate>>>>>>>>>>"+lastFourWeekDate)
      val lastDate = DateTimeFormatter.BASIC_ISO_DATE.format(date.minusDays(4))
      println("lastDate>>>>>>>>>>"+lastDate)*/


    /*    val dateDtLastD = new Date()
        val calLastD = Calendar.getInstance
        calLastD.setTime(dateDtLastD)
        calLastD.add(Calendar.DATE, -4)
        val rDataMpLastDt =((calLastD.getTime.getTime) + 8 * 1000 * 3600) / (3600 * 1000 * 24) % 2

        println("rDataMpLastDt>>>>>>>>rDataMpLastDt>>>>>>>>>>>>>rDataMpLastDt>>>>>>>>>>>>" + rDataMpLastDt)*/
    //val sdf = new SimpleDateFormat("yyyyMMdd")
    //    //    val begDate: Date = sdf.parse(begDay)
    //
    //    val begDate: Date = new Date(System.currentTimeMillis() - 24*60*60*1000)    //当天的前一天
    //    val begDay: String = sdf.format(begDate)
    //
    //   println(new Date(System.currentTimeMillis() - 24*60*60*1000))
    //    println(new Date(System.currentTimeMillis()))
    //    println(begDay)
    //    println("------统计时间："+begDate.toString())
    val begDate: Date = new Date(System.currentTimeMillis() - 24 * 60 * 60 * 1000)

    g(begDate)


  }

  private def g(begDate: Date): Unit = {
    val sdf = new SimpleDateFormat("yyyyMMdd")
    val stat_date: String = sdf.format(begDate)
    //yyyymmdd
    val today: Date = begDate
    val fst: Date = sdf.parse("19700101")
    val days: Long = (today.getTime - fst.getTime) / (1000 * 60 * 60 * 24)
    val dt: Long = days % 2
    println("stat_date: " + stat_date)
    println("today: " + today)
    println("dt: " + dt)
  }
}
