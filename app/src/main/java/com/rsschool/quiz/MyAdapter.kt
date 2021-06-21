package com.rsschool.quiz

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.rsschool.quiz.databinding.FragmentQuizBinding

class MyAdapter(activity: FragmentActivity) :
    FragmentStateAdapter(activity){
    private val myQuestions = QuestionsData.questions
    var myAnswers = mutableListOf<Answer>(
        Answer("",""),
        Answer("",""),
        Answer("",""),
        Answer("",""),
        Answer("",""))
    var listOfAnswers = mutableListOf<String>("1","2","3","4","5")
    val result:Int = 0
/*
    inner class MyViewHolder(val binding: FragmentQuizBinding) :
        RecyclerView.ViewHolder(binding.root)
*/


    override fun getItemCount(): Int {
        return myQuestions.size+1
    }

    override fun createFragment(position: Int): Fragment {
        return if(position < myQuestions.size) FragmentQuiz(myQuestions, myAnswers, position, result,listOfAnswers)
        else LastFragment()
    }



}