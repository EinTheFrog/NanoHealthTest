package com.example.nanohealthtest.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.nanohealthtest.R
import com.example.nanohealthtest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}