package com.example.horizontalscrollview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageView.setImageResource(R.drawable.palestine)
        imageView2.setImageResource(R.drawable.india)
        imageView3.setImageResource(R.drawable.iraq)
        imageView4.setImageResource(R.drawable.germany)
        imageView5.setImageResource(R.drawable.spain)
    }
}