package com.rsschool.quiz

import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.viewpager2.widget.ViewPager2
import com.rsschool.quiz.databinding.FragmentLastBinding
import kotlin.system.exitProcess

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_ANSWERS = "Answers"


class LastFragment : Fragment() {
    private var _binding: FragmentLastBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    // TODO: Rename and change types of parameters
    private var answersParam: MutableList<Answer>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            answersParam = it.getParcelableArrayList(ARG_ANSWERS)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLastBinding.inflate(inflater, container, false)
        var result: Int = 0
        val trueAnswers = listOf<String>("0","Кур","Брюссель","ЮАР","Азот",)
        for(valu in answersParam!!){
            if(trueAnswers.contains(valu.answer)){
                result += 20
            }
        }
       // binding.textViewResult.text = answersParam.toString()
        binding.textViewResult.text = result.toString()
        //    Log.i("LastFragment", "${AnswersData.myAnswers[0].answer}")
        val viewPager2 = activity?.findViewById<ViewPager2>(R.id.my_view_pager)
        //back button
        binding.imageViewBack.setOnClickListener {
            viewPager2?.currentItem = 0
        }
        binding.imageViewClose.setOnClickListener {
            requireActivity().finish()
            exitProcess(0)
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        @JvmStatic
        fun newInstance(param1Answers: MutableList<Answer>) =
            LastFragment().apply {
                arguments = bundleOf(
                    ARG_ANSWERS to param1Answers
                    //    putString(ARG_PARAM2, param2)
                )
            }
    }
}