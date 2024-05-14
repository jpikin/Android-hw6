package com.example.hw6

import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.animation.TypeEvaluator
import android.animation.ValueAnimator
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Shader
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
    private val textColor = PropertyValuesHolder.ofInt(
        "textColor",
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

        val args: FsendArgs by navArgs()
        binding.fsendResultText.text = args.myArg

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val button = binding.restartButton
        val buttonShader = LinearGradient(
            0f, 0f,
            button.paint.measureText(button.text.toString()),
            button.textSize,
            intArrayOf(Color.WHITE, Color.RED, Color.WHITE),
            null,
            Shader.TileMode.CLAMP
        )
        button.paint.shader = buttonShader
        button.invalidate()

        ValueAnimator.ofObject(
            GradientArgbEvaluator,
            intArrayOf(Color.WHITE, Color.WHITE, Color.WHITE),
            intArrayOf(Color.WHITE, Color.WHITE, Color.BLUE),
            intArrayOf(Color.WHITE, Color.BLUE, Color.RED),
            intArrayOf(Color.BLUE, Color.RED, Color.YELLOW),
            intArrayOf(Color.RED, Color.YELLOW, Color.CYAN),
            intArrayOf(Color.YELLOW, Color.CYAN, Color.BLACK),
            intArrayOf(Color.CYAN, Color.BLACK, Color.WHITE),
            intArrayOf(Color.BLACK, Color.WHITE, Color.BLUE),
            intArrayOf(Color.WHITE, Color.BLUE, Color.GREEN),
        ).apply {
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
            duration = 3000
            addUpdateListener {
                val button = binding.restartButton
                val shader = LinearGradient(
                    0f, 0f,
                    button.paint.measureText(button.text.toString()),
                    button.textSize,
                    it.animatedValue as IntArray,
                    null,
                    Shader.TileMode.CLAMP)
                button.paint.shader = shader
                button.invalidate()
                start()
            }
        }

        ObjectAnimator.ofPropertyValuesHolder(binding.fsendResultText, rotationY, textColor).apply {
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

    object GradientArgbEvaluator : TypeEvaluator<IntArray> {
        val argbEvaluator = ArgbEvaluator()
        override fun evaluate(
            fraction: Float,
            startValue: IntArray,
            endValue: IntArray
        ): IntArray {
            return startValue.mapIndexed { index, item ->
                argbEvaluator.evaluate(fraction, item, endValue[index]) as Int
            }.toIntArray()
        }
    }
}