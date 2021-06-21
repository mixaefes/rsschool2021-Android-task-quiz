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
/*    private val questions = mutableListOf<Question>(
        Question(text = "Какого газа в атмосфере больше всего",answers = mutableListOf("Азот","Водород","Кислород","Углерод","Воландеморд")),
        Question(text = "Какой римской цифры не существует",answers = mutableListOf("0","1000","10000","101000","666")),
        Question(text = "Алектрофобия это боязнь:",answers = mutableListOf("Кур","Собак","Алектричества","Зайцев","Боязни")),
        Question(text = "Больше одной столицы в:",answers = mutableListOf("ЮАР","Алжире","Тунисе","Израиле","Мексике")),
        Question(text = "Столица ЕС",answers = mutableListOf("Брюссель","Берлин","Кельн","Вена","Гаага"))
    )*/

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