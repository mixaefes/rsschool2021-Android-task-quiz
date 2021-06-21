package com.rsschool.quiz

class QuestionsData {
    companion object{
    val questions = arrayOf<Question>(
        Question(text = "Какого газа в атмосфере больше всего",
            answers = arrayOf("Азот" to true,"Водород" to false,"Кислород" to false,"Углерод" to false,"Воландеморд" to false)),
        Question(text = "Какой римской цифры не существует",
            answers = arrayOf("0" to true,"1000" to false,"10000" to false,"101000" to false,"666" to false)),
        Question(text = "Алектрофобия это боязнь:",
            answers = arrayOf("Кур" to true,"Собак" to false,"Алектричества" to false,"Зайцев" to false,"Боязни" to false)),
        Question(text = "Больше одной столицы в:",
            answers = arrayOf("ЮАР" to true,"Алжире" to false,"Тунисе" to false,"Израиле" to false,"Мексике" to false)),
        Question(text = "Столица ЕС",
            answers = arrayOf("Брюссель" to true,"Берлин" to false,"Кельн" to false,"Вена" to false,"Гаага" to false))
    )

    }
}