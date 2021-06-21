package com.rsschool.quiz

class QuestionsData {
    companion object{
    val questions = mutableListOf<Question>(
        Question(text = "Какого газа в атмосфере больше всего",
            answers = mutableListOf("Азот","Водород","Кислород","Углерод","Воландеморд")
        ),
        Question(text = "Какой римской цифры не существует",
            answers = mutableListOf("0","1000" ,"10000" ,"101000" ,"666" )),
        Question(text = "Алектрофобия это боязнь:",
            answers = mutableListOf("Кур","Собак" ,"Алектричества" ,"Зайцев" ,"Боязни" )),
        Question(text = "Больше одной столицы в:",
            answers = mutableListOf("ЮАР","Алжире" ,"Тунисе" ,"Израиле" ,"Мексике" )),
        Question(text = "Столица ЕС",
            answers = mutableListOf("Брюссель","Берлин" ,"Кельн" ,"Вена" ,"Гаага" ))
    )

    }
}