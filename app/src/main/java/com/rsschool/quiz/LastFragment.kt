package com.rsschool.quiz

import android.content.Context
import android.content.Intent
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
import java.lang.StringBuilder
import kotlin.system.exitProcess


private const val ARG_ANSWERS = "Answers"
private var listener:LastFragment.ActionBackListener? = null

class LastFragment : Fragment() {
    private var _binding: FragmentLastBinding? = null

    private val binding get() = _binding!!
    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as ActionBackListener
    }


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
        binding.textViewResult.text = "You result: ${result.toString()}%"
        val viewPager2 = activity?.findViewById<ViewPager2>(R.id.my_view_pager)
        //back button
        binding.imageViewBack.setOnClickListener {
         listener?.onActionBack()
        }
        binding.imageViewClose.setOnClickListener {
            requireActivity().finish()
            exitProcess(0)
        }
        binding.imageViewShare.setOnClickListener {
            sendMessage(result)
        }
        return binding.root
    }

    private fun sendMessage(result:Int) {
       var myResult =  with(StringBuilder()){
            appendLine("Your result: $result %")
            for(str in answersParam!!.indices){
                appendLine()
                appendLine("${str+1} ${answersParam!![str].question}")
                appendLine("Your answer: ${answersParam!![str].answer}")
            }
            toString()
        }
        val intent = Intent(Intent.ACTION_SEND)
        intent.putExtra(Intent.EXTRA_SUBJECT, "Quiz result")
        intent.putExtra(Intent.EXTRA_TEXT,myResult)
        intent.type = "text/plain"
        startActivity(intent)
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
    interface ActionBackListener{
        fun onActionBack()
    }
}