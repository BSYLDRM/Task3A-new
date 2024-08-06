package com.example.tasks3application.counter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tasks3application.R
import com.example.tasks3application.databinding.ActivityCounterBinding

class CounterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCounterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCounterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, CounterFragment())
                .commitNow()
        }
    }
}
