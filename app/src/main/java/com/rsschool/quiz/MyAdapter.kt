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

class MyAdapter(val questions:List<Question>,activity: FragmentActivity) :
    FragmentStateAdapter(activity){

    val myAnswers = mutableListOf<Answer>(
        Answer("",""),
        Answer("",""),
        Answer("",""),
        Answer("",""),
        Answer("",""))
 //   var listOfAnswers = mutableListOf<String>("1","2","3","4","5")
//    val result:Int = 0
/*
    inner class MyViewHolder(val binding: FragmentQuizBinding) :
        RecyclerView.ViewHolder(binding.root)
*/


    override fun getItemCount(): Int {
        return questions.size+1
    }

    override fun createFragment(position: Int): Fragment {
        return if (position <= 4){FragmentQuiz(questions[position], myAnswers, position)}
        else {
            LastFragment.newInstance(myAnswers)
        }
    }


}