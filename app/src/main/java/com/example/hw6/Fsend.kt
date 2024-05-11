package com.example.hw6

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.hw6.databinding.FragmentFsendBinding



class Fsend : Fragment() {

    private var _binding: FragmentFsendBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFsendBinding.inflate(inflater)

        val args : FsendArgs by navArgs()
        binding.fsendResultText.text = args.myArg

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.restartButton.setOnClickListener {
            findNavController().navigate(R.id.action_fsend_to_fquestions)

        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}