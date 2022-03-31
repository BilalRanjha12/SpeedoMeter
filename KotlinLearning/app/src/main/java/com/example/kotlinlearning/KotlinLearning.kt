package com.example.kotlinlearning

fun main() {

   /* val months = mutableListOf<String>("jan", "feb", "march")
    months.add("april")
    months[2] = "dec"
    months.removeAt(1)
    print(months)
    val anyTypes = listOf("1", 1, 2, 3, true, false)
    print(anyTypes.size)
    print(months[2])
    for (month in months)
        print(month)
    val additionalMonths = months.toMutableList()
    val newMonths = arrayOf("april", "may", "june")
    additionalMonths.addAll(newMonths)
    additionalMonths.add("july  ")
    print(additionalMonths)
    val days = mutableListOf<String>("mon", "tue", "wed")
    days.add("thurs")
    print(days)
    val fruits = setOf("apple","apple","banana","mango","orange","mango")
    val newFruits=fruits.toMutableList()
    newFruits.add("pear")
    println(newFruits)
val daysOfWeek= mapOf(1 to "monday", 2 to "Tuesday", 3 to "wednesday")
for (key in daysOfWeek.keys)

    print("$key is to ${daysOfWeek[key]}")

        val myArrayList: ArrayList<Double> = ArrayList()
        myArrayList.add(13.212312)
        myArrayList.add(23.151232)
        myArrayList.add(32.651553)
        myArrayList.add(16.223817)
        myArrayList.add(18.523999)
        var total = 0.0
        for (i in myArrayList){
            total += i
        }
        var average = total / myArrayList.size
        println("Avarage is " + average)

    var num=1.2
    var bil=1.2
    var c =1.2
    if(num>bil&&num>c)
        println("$num is greater")
     else if(bil>num&&bil>c)

        println("$bil is greater")
    else
        println("$c is greater")
*/
}
fun greatestAmongThree(num:Int, bil: Int, c: Int){

    if(num>bil&&num>c)
    else if(bil>num&&bil>c)

        println("$bil is greater")
    else
        println("$c is greater")
}
