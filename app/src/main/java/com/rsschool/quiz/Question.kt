package com.rsschool.quiz

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Question(val text:String, val answers:MutableList<String>):Parcelable
