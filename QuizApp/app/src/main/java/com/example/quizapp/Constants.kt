package com.example.quizapp

object Constants {

    fun getQuestions():ArrayList<Question>{
val questionList=ArrayList<Question>()

val que1=Question(
    1,"What country does this flag belong to?",
    R.drawable.pakistan,
    "Argentina","Australia",
    "Pakistan","USA",3
)
        questionList.add(que1)
        return  questionList
        //2
        val que2=Question(
            2,"What country does this flag belong to?",
            R.drawable.saudiarabia,
            "Australia","India",
            "Austria","SaudiArabia",4
        )
        questionList.add(que2)
        return  questionList
        //3
        val que3=Question(
            3,"What country does this flag belong to?",
            R.drawable.usa,
            "Iraq","USA",
            "Pakistan","Iran",2
        )
        questionList.add(que3)
        return  questionList
        //4
        val que4=Question(
            4,"What country does this flag belong to?",
            R.drawable.turkey,
            "Turkey","Pakistan",
            "UK","USA",1
        )
        questionList.add(que1)
        return  questionList
        //5
        val que5=Question(
            5,"What country does this flag belong to?",
            R.drawable.pakistan,
            "Pakistan","Australia",
            "Norway","India",1
        )
        questionList.add(que5)
        return  questionList
    }
}