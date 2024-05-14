package com.example.hw6

import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.hw6.databinding.FragmentFsendBinding



class Fsend : Fragment() {

    private var _binding: FragmentFsendBinding? = null
    private val binding get() = _binding!!

    private val rotationY = PropertyValuesHolder.ofFloat(View.ROTATION_Y, 0f, 720f)
    private val textColor = PropertyValuesHolder.ofInt("textColor",
        Color.parseColor("#FF000000"),
        Color.parseColor("#FFD700")
    ).apply {
        setEvaluator(ArgbEvaluator())
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

        ObjectAnimator.ofPropertyValuesHolder(binding.fsendResultText,rotationY,textColor).apply {
            duration = 5000
            interpolator = AccelerateInterpolator()
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
            start()
        }

        binding.restartButton.setOnClickListener {
            findNavController().navigate(R.id.action_fsend_to_fquestions)

        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}