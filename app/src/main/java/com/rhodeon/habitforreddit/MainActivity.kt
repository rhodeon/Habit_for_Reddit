package com.rhodeon.habitforreddit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rhodeon.habitforreddit.databinding.ActivityMainBinding
import com.rhodeon.habitforreddit.network.oauth.applicationOnlyRequest

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            applicationOnlyRequest()
        }
    }
}