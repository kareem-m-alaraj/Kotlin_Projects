package com.example.helloworld

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_two_activities.*

class TwoActivities : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_two_activities)

        sendBtn.setOnClickListener {
            var msg = message.text.toString()
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("msg", msg)
            startActivity(intent)
        }
    }
}