package com.rhodeon.habitforreddit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.rhodeon.habitforreddit.databinding.ActivityMainBinding
import com.rhodeon.habitforreddit.network.api.SubredditRequests
import com.rhodeon.habitforreddit.network.oauth.requestAccessToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var token: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                val newToken = requestAccessToken()
                Toast.makeText(this@MainActivity, "$newToken", Toast.LENGTH_SHORT).show()
                if (newToken != null) {
                    token = newToken
                }
            }
        }

        binding.subreddits.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                val request = SubredditRequests(token)
                request.subreddits()
            }
        }
    }
}