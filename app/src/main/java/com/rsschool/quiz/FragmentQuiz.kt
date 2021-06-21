package com.rsschool.quiz

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.RadioButton
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getColor
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.rsschool.quiz.databinding.FragmentQuizBinding

class FragmentQuiz(
    val questions: Question,
    val myAnswers: MutableList<Answer>,
    val position: Int,
) : Fragment() {
    private var _binding: FragmentQuizBinding? = null

    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setTheme()
        val shuffledAnswers:MutableList<String> = questions.answers
        shuffledAnswers.shuffle()
        _binding = FragmentQuizBinding.inflate(inflater, container, false)
        val viewPager = activity?.findViewById<ViewPager2>(R.id.my_view_pager)
        //my toolbar
        val toolbar = binding.toolbar
        //set question
        binding.question.text = questions.text
        //set toolbar tittle
        binding.toolbar.title = "Question: ${position.plus(1)}"

        binding.optionOne.text = shuffledAnswers[0]
        binding.optionTwo.text = shuffledAnswers[1]
        binding.optionThree.text = shuffledAnswers[2]
        binding.optionFour.text = shuffledAnswers[3]
        binding.optionFive.text = shuffledAnswers[4]
        //set visibility for next and prev buttons
        binding.previousButton.isEnabled = position>0
        if (position<1){
            toolbar.navigationIcon = null
        }
        //binding.nextButton.visibility = View.INVISIBLE
        binding.nextButton.isEnabled = false
        //set button submit in the last view
        if (position == 4) {
            binding.nextButton.text = "SUBMIT"
        }

        //next button
        binding.radioGroup.setOnCheckedChangeListener { group, checkedId ->
          //  binding.nextButton.visibility = View.VISIBLE
            binding.nextButton.isEnabled = true

            val radio = view?.findViewById<RadioButton>(checkedId)
            // setAnswers[position] = radio?.text.toString()
            binding.nextButton.setOnClickListener {
                if (position <= 5) {
                    myAnswers[position].question = binding.question.text.toString()
                    myAnswers[position].answer = radio?.text.toString()

                    viewPager?.currentItem = position + 1

                } else {

                    myAnswers[position].question = binding.question.text.toString()
                    myAnswers[position].answer = radio?.text.toString()

                    viewPager?.currentItem = position + 1

                }
            }
        }

        //previous buttons
    toolbar.setNavigationOnClickListener {
    if (position >= 1) {
        viewPager?.currentItem = position.minus(1)
    } else {
        Toast.makeText(context, "its start", Toast.LENGTH_SHORT).show()
    }
}
        binding.previousButton.setOnClickListener {
            if (position >= 1) {
                viewPager?.currentItem = position.minus(1)
            } else {
                Toast.makeText(context, "its start", Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root
    }

    private fun setTheme() {
        when (position) {
            0 -> context?.theme?.applyStyle(R.style.Theme_Quiz_First, true)
            1 -> context?.theme?.applyStyle(R.style.Theme_Quiz_Second, true)
            2 -> context?.theme?.applyStyle(R.style.Theme_Quiz_Third, true)
            3 -> context?.theme?.applyStyle(R.style.Theme_Quiz_Four, true)
            else -> context?.theme?.applyStyle(R.style.Theme_Quiz_Five, true)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.i("FragmentQuiz", "onAttach called")
    }

    override fun onDetach() {
        super.onDetach()
        Log.i("FragmentQuiz", "onDetach called")

    }
}