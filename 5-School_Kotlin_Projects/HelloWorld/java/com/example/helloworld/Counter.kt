package com.example.helloworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_counter.*

class Counter : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_counter)

        var counter = 0
        count_view.text = counter.toString()

        count_btn.setOnClickListener {
            counter += 1
            count_view.text = counter.toString()
        }

        toast_btn.setOnClickListener {
            Toast.makeText(this, counter.toString(), Toast.LENGTH_SHORT).show()
        }
    }
}