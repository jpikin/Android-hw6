package com.example.hw6


import android.animation.AnimatorInflater
import android.animation.ObjectAnimator
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.findNavController
import com.example.hw6.databinding.FragmentFquestionsBinding



class Fquestions : Fragment() {

    private var _binding: FragmentFquestionsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFquestionsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val animatorInflater = AnimatorInflater
            .loadAnimator(context, R.animator.custom_animation)
            as ObjectAnimator

        animatorInflater.apply {target = binding.qImage}.start()

        binding.qBackButton.setOnClickListener {
            parentFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
            findNavController().navigate(R.id.action_fquestions_to_fmain)
        }

        binding.qSendButton.setOnClickListener {
            val result = getAnswersByUser()
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
        val yourResult = resources.getText(R.string.your_result)
        val div3 = resources.getText(R.string.div3)

        if (binding.question1.checkedRadioButtonId == binding.answer12.id) correctAnswersCount++
        if (binding.question2.checkedRadioButtonId == binding.answer21.id) correctAnswersCount++
        if (binding.question3.checkedRadioButtonId == binding.answer31.id) correctAnswersCount++

        return "$yourResult $correctAnswersCount$div3"
    }
}