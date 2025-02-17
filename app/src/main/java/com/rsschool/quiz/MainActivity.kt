package com.rsschool.quiz

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.fragment.app.FragmentTransaction
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.rsschool.quiz.databinding.ActivityMainBinding
import com.rsschool.quiz.databinding.FragmentQuizBinding
import java.util.*

class MainActivity : AppCompatActivity(),LastFragment.ActionBackListener{
    val myQuestions = QuestionsData.questions
    private lateinit var binding:ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val myAdapter = MyAdapter(questions = myQuestions,this)
        binding.myViewPager.adapter = myAdapter
    binding.myViewPager.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback(){
        @RequiresApi(Build.VERSION_CODES.M)
        override fun onPageSelected(position: Int) {
            when(position){
                0 ->window.statusBarColor = ContextCompat.getColor(applicationContext, R.color.deep_orange_100_dark)
                1 ->window.statusBarColor = ContextCompat.getColor(applicationContext, R.color.yellow_100_dark)
                2 ->window.statusBarColor = ContextCompat.getColor(applicationContext, R.color.deep_pink_100_dark)
                3 ->window.statusBarColor = ContextCompat.getColor(applicationContext, R.color.deep_brown_100_dark)
                else ->window.statusBarColor = ContextCompat.getColor(applicationContext, R.color.deep_indigo_100_dark)
            }
            super.onPageSelected(position)
        }
    })
    }

    override fun onActionBack() {
        val adapter = MyAdapter(myQuestions,this)
        binding.myViewPager.adapter = adapter
    }


}