package com.example.tasks3application.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tasks3application.counter.CounterActivity
import com.example.tasks3application.databinding.ActivityMainBinding
import com.example.tasks3application.game.GameActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonCounter.setOnClickListener {
            val intent = Intent(this, CounterActivity::class.java)
            startActivity(intent)
        }
        binding.buttonGame.setOnClickListener {
            val intent = Intent(this, GameActivity::class.java)
            startActivity(intent)
        }
    }
}
