package com.rhodeon.habitforreddit

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.edit
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.rhodeon.habitforreddit.databinding.ActivityMainBinding
import com.rhodeon.habitforreddit.network.api.SubredditRequests
import com.rhodeon.habitforreddit.network.oauth.requestAccessToken
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var token: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
        runBlocking {
            CoroutineScope(Dispatchers.Main).launch {
                withContext(Dispatchers.Main) {
                    val newToken = requestAccessToken()
                    Toast.makeText(this@MainActivity, "$newToken", Toast.LENGTH_SHORT).show()
                    if (newToken != null) {
                        token = newToken
                    }

                    getSharedPreferences("vars", Context.MODE_PRIVATE).edit {
                        putString("token", token)
                    }

                    val request = SubredditRequests(token)
                    request.subreddits()

//
//                    val navController = findNavController(R.id.nav_host_fragment)
//                    val navGraph = navController.navInflater.inflate(R.navigation.main_nav)
//                    navGraph.startDestination = R.id.homeFragment
//                    navController.graph = navGraph
//                    binding.bottomNavigationView.setupWithNavController(navController)
                }


            }
        }
        }




    }
//
//
//        binding.subreddits.setOnClickListener {
//            CoroutineScope(Dispatchers.Main).launch {
//                val request = SubredditRequests(token)
//                request.subreddits()
//            }
//        }

}