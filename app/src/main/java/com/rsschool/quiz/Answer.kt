package com.rsschool.quiz

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Answer(var question:String, var answer:String):Parcelable{
    override fun toString(): String {
        return "question: $question , answer: $answer"
    }
}
