package com.example.hw6

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import androidx.fragment.app.replace
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

        binding.qBackButton.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
        binding.qSendButton.setOnClickListener {
            parentFragmentManager.commit {
                replace<Fsend>(R.id.main_container)
                addToBackStack(Fsend::class.java.simpleName)
            }
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = Fquestions()
    }
}