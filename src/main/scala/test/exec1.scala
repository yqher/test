package test

/*编写Computer类，包含CPU、内存、硬盘等属性，getDetails方法用于返回Computer的详细信息
编写PC子类，继承Computer类，添加特有属性【品牌brand】
编写NotePad子类，继承Computer类，添加特有属性【颜色color】
编写Test Object，在main方法中创建PC和NotePad对象，分别对象中特有的属性赋值，以及从Computer类继承的属性赋值，并使用方法并打印输出信息。*/
object exec1 {
  def main(args: Array[String]): Unit = {
    val pc = new PC
    val pad = new NotePad
    pc.brand = "hua"
    pad.color = "black"
    pc.getDetails
    pad.getDetails
    val pc1: PC = new PC


  }
}

class Computer {
  var cpu: String = "intel"
  var memory = "4G"
  var disk = "500G"

  def getDetails = {
    println(s"cpu:$cpu,memory:$memory,disk:$disk")
  }
}

class PC extends Computer {
  var brand = "deil"
}

class NotePad extends Computer {
  var color = "red"
}

