package com.example.helloworld

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_colors.*

class ColorsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_colors)

        blackBtn.setOnClickListener {
            colorsView.setBackgroundColor(Color.BLACK)
        }
        redBtn.setOnClickListener {
            colorsView.setBackgroundColor(Color.RED)
        }
        greenBtn.setOnClickListener {
            colorsView.setBackgroundColor(Color.GREEN)
        }
        blueBtn.setOnClickListener {
            colorsView.setBackgroundColor(Color.BLUE)
        }
    }
}