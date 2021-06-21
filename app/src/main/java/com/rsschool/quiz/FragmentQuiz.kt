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

class FragmentQuiz(val questions:Array<Question>,var answers:MutableList<Answer>,val position:Int,val result:Int,var setAnswers:MutableList<String>): Fragment() {
    private var _binding: FragmentQuizBinding? = null
    // This property is only valid between onCreateView and
// onDestroyView.
    private val binding get() = _binding!!
//    private var listener: ActionPerformedListener? = null
//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        listener = context as ActionPerformedListener
//    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setTheme()
        _binding = FragmentQuizBinding.inflate(inflater, container, false)
        //set question
        binding.question.text =questions[position].text
        //set toolbar tittle
        binding.toolbar.title = "Question: ${position.plus(1)}"
        val shuffledAnswers = questions[position].answers
        shuffledAnswers.shuffle()
        binding.optionOne.text = shuffledAnswers[0].first
        binding.optionTwo.text = shuffledAnswers[1].first
        binding.optionThree.text = shuffledAnswers[2].first
        binding.optionFour.text = shuffledAnswers[3].first
        binding.optionFive.text = shuffledAnswers[4].first
        val viewPager = activity?.findViewById<ViewPager2>(R.id.my_view_pager)
        //set visibility for next and prev buttons
        binding.previousButton.visibility = if(position < 1) View.INVISIBLE else View.VISIBLE
        binding.nextButton.visibility = View.INVISIBLE
        //set button submit in the last view
        if(position==questions.size-1){
            binding.nextButton.text = "SUBMIT"
        }

        //next button
        binding.radioGroup.setOnCheckedChangeListener { group, checkedId ->
            binding.nextButton.visibility = View.VISIBLE
            answers[position].question = binding.question.text.toString()

            val radio: RadioButton? = view?.findViewById(checkedId)
            answers[position].answer = radio?.text.toString()
            setAnswers[position] = radio?.text.toString()
                binding.nextButton.setOnClickListener {
                if(position+1 < questions.size) {
                    viewPager?.currentItem = position.plus(1)
                    Log.i("FragmentQuizzz", "position: $position")

                }else{
                    Log.i("FragmentQuizzz","mySetAnswers:  ${setAnswers.toMutableSet()}")
                    val sP = context?.getSharedPreferences("STORAGE_ANSWERS",Context.MODE_PRIVATE)
                    val editor = sP?.edit()
                    editor?.putStringSet("MY_ANSWERS_SET", setAnswers.toMutableSet())
                    editor?.commit()
                    viewPager?.currentItem = position.plus(1)

                }
            }
        }

        //previous button
        binding.previousButton.setOnClickListener {
            if(position >= 1) {
                Log.i("FragmentQuiz","currentItem = ${viewPager?.currentItem}")
                viewPager?.currentItem = position.minus(1)
            }else{
                Toast.makeText(context, "its start", Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root
    }

    private fun setTheme() {
        when(position){
            0 ->context?.theme?.applyStyle(R.style.Theme_Quiz_First, true)
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

  /*  interface ActionPerformedListener {
        fun onActionPerformed(answers: Array<Answer>)
    }*/
}