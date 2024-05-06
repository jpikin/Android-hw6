package com.example.hw6

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.hw6.databinding.FragmentFmainBinding




class Fmain : Fragment() {


    private var _binding: FragmentFmainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFmainBinding.inflate(inflater)
        binding.startButton.setOnClickListener {
            parentFragmentManager.commit {
                replace<Fquestions>(R.id.main_container)
                addToBackStack(Fquestions::class.java.simpleName)
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
        fun newInstance() = Fmain()
    }
}