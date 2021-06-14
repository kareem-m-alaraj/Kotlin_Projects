package com.example.myfirstproject

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.toDrawable
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        blueBtn.setOnClickListener {
            boardView.setBackgroundColor(Color.BLUE)
        }
        greenBtn.setOnClickListener {
            boardView.setBackgroundColor(Color.GREEN)
        }
        blackBtn.setOnClickListener {
            boardView.setBackgroundColor(Color.BLACK)
        }
        redBtn.setOnClickListener {
            boardView.setBackgroundColor(Color.RED)
        }

        backBtn.setOnClickListener {
            var intent = Intent(this, FirstActivity::class.java)
            startActivity(intent)
        }
    }
}