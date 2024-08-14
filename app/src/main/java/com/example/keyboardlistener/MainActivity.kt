package com.example.keyboardlistener

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val rootView = findViewById<ConstraintLayout>(R.id.main)
        val listener = KeyboardListener(rootView)
        listener.asLivedata.observe(this) { result ->
            Log.d("tag", "livedata:$result")

        }
        val flow = listener.asStateFlow
        lifecycleScope.launch {
            flow.collect { result ->
                Log.d("tag", "flow: $result")
            }
        }
    }
}