package com.example.hw6

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.hw6.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

//        val binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
        setContentView(R.layout.activity_main)

        supportFragmentManager.commit{
            replace<Fmain>(R.id.main_container)
            addToBackStack(Fmain::class.java.simpleName)
        }
//            .beginTransaction().replace(R.id.test_frame, Fmain.newInstance())
//            .commit()
    }
}