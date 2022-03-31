package com.example.datatypes

fun main() {
    /*
        val iphone = MobilePhone("iOS", "Apple", "iPhone 12")
        val galaxyS20 = MobilePhone("Android", "Samsung", "Galaxy S20")
        val mateXS = MobilePhone("Android", "Huawei", "Mate X S")

    }

    var result=addUp(5 ,6)
    println("result is $result")
}



    var courseName: String="android masterclass"
    var leet : Float=13.6F
    val pi : Double = 3.14159265358979
    var age : Byte = 25
    var year : Short = 2020
    var phoneNumber: Long = 18881234567
    var isGood : Boolean = true
    var firstCharacter : Char = 'a'
var myNum=5
    myNum+=3
    myNum*=4
    myNum++

     println("MyNum is${myNum++}")
     println("MyNum is${++myNum}")
     println("MyNum is${myNum--}")
    var season = 0

    when (season)
    {
        in 1..2 -> println("spring")
        in 3..4 -> println("Summer")
        in 5..6 -> println("autumn")
        in 12 downTo 2-> println("bilal")
        else -> println("invalid")
    }
    var x =100
    do
    {
       print("$x")

    }

    while (x<=0)



    println("while has finished")
    for (num in 1..10000){
        if (num==9001)
            println("its over 9001")
}

   var humidity ="humid"
    var humiditylevel=80
while (humidity=="humid"){
    humiditylevel-=5
    println("humidity level is decreased")
    if (humiditylevel<=60){
        humidity="comfy"
    println("humidity level is comfy")
}}
for (num in 1 until 20)
{

    if (num/2==5)
    {
        continue

    }
    println ("$num")
}
    println("done with the loop")


    fun addUp(a: Int, b: Int): Int {
        return a+b

    class MobilePhone(osName: String, brand: String, model: String){
        init {
            println("The phone $model from $brand uses $osName as its Operating System")


        }

val no1= 3
    val no2=no1.toInt()
    println("so value has been changed into int $no2")*/
val numbers= arrayOf(1,2,3,4,5,6,7)
    val numbers1= arrayOf(1.0,2.0,3.0,4.0,5.0,6.0,7.0)
   // print("initial value ${numbers.contentToString()}")
    //for (element in numbers)
       // println("${element+3 }")
   // print(numbers[5])
    numbers1[0]= 6.0
    numbers1[4]=3.0
    numbers1[1]=4.0
   // print("\n initial value ${numbers.contentToString()}")
    print("\n initial value ${numbers1.contentToString()}")
    val days= arrayOf("sunday","monday","tuesday","wednesday")
    print("\nhello ${days.contentToString()}")
    val fruits = arrayOf(Fruit("apple",2.5),Fruit("banana",166.4),Fruit("mango",60.5))
print(fruits.contentToString())
    for (index in fruits.indices){
        println("${fruits[index].name} is in index$index")
    }
}
data class Fruit(val name : String, val price: Double)




