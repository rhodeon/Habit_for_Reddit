package com.rhodeon.habitforreddit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.rhodeon.habitforreddit.databinding.ActivityMainBinding
import com.rhodeon.habitforreddit.extensions.collapse
import com.rhodeon.habitforreddit.extensions.show
import com.rhodeon.habitforreddit.network.oauth.requestAccessToken
import com.rhodeon.habitforreddit.utils.SessionManager
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        CoroutineScope(Dispatchers.Main).launch {
            val newToken = requestAccessToken()
            Toast.makeText(this@MainActivity, "$newToken", Toast.LENGTH_SHORT).show()
            if (newToken != null) {
                SessionManager.token = newToken
            }

            val navController = findNavController(R.id.nav_host_fragment)
            val navGraph = navController.navInflater.inflate(R.navigation.main_nav)
            navGraph.startDestination = R.id.homeFeedFragment
            navController.graph = navGraph
            binding.bottomNavigationView.setupWithNavController(navController)

            navController.addOnDestinationChangedListener { _, destination, _ ->
                when (destination.id) {
                    R.id.threadFragment -> binding.bottomNavigationView.collapse()
                    else -> binding.bottomNavigationView.show()
                }
            }
        }
    }
}