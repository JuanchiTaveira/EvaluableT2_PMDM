package com.example.evaluablet2.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.evaluablet2.databinding.ActivitySecondBinding

class SecondActivity: AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySecondBinding.inflate(layoutInflater)
    }
}