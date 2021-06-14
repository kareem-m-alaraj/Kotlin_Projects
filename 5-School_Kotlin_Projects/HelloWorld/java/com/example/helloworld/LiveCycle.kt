package com.example.helloworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast

class LiveCycle : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_live_cycle)
    }
    override fun onResume() {
        super.onResume()
        Toast.makeText(this, "اهلا مجدداً", Toast.LENGTH_SHORT).show()
        Log.d("LOG_TAG", "onResume");
    }

    override fun onPause() {
        super.onPause()
        Toast.makeText(this, "وين رايح", Toast.LENGTH_SHORT).show()
        Log.d("LOG_TAG", "onPause");
    }
}