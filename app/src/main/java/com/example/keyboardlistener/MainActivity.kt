package com.example.keyboardlistener

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(com.example.keyboardlistener.R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(com.example.keyboardlistener.R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val rootView = findViewById<ConstraintLayout>(com.example.keyboardlistener.R.id.main)
        val listener = KeyboardListener(rootView)
        listener.getListener().observe(this) { result ->
            Log.d("tag", "$result")

        }
    }
}