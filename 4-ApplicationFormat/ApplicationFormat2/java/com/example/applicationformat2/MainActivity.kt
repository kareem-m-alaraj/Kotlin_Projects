package com.example.applicationformat2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    override fun onResume() {
        Toast.makeText(this, R.string.toast, Toast.LENGTH_SHORT).show();
        super.onResume()
    }
}