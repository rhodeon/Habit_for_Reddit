package com.rhodeon.habitforreddit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.rhodeon.habitforreddit.databinding.ActivityMainBinding
import com.rhodeon.habitforreddit.network.oauth.requestAccessToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                Toast.makeText(this@MainActivity, "${requestAccessToken()}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}