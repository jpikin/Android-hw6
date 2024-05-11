package com.example.hw6

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import com.example.hw6.databinding.FragmentFquestionsBinding



class Fquestions : Fragment() {



    private var _binding: FragmentFquestionsBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFquestionsBinding.inflate(inflater)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        binding.qBackButton.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
        binding.qSendButton.setOnClickListener {
            val result = getAnswersByUser()
//            val bundle = Bundle()
//            bundle.putString("MyArg", result)
//
//            findNavController().navigate(R.id.action_fquestions_to_fsend)
          val action = FquestionsDirections.actionFquestionsToFsend(result)
          findNavController().navigate(action)


        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getAnswersByUser(): String {
        var correctAnswersCount = 0

        if (binding.question1.checkedRadioButtonId == binding.answer12.id) correctAnswersCount++
        if (binding.question2.checkedRadioButtonId == binding.answer21.id) correctAnswersCount++
        if (binding.question3.checkedRadioButtonId == binding.answer31.id) correctAnswersCount++

        return "Ваш результат: $correctAnswersCount/3"
    }
}