package com.example.myfirstproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_images.*

class ImagesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_images)


        palestineBtn.setOnClickListener {
            imageView.setImageResource(R.drawable.palestine)
        }
        turkeyBtn.setOnClickListener {
            imageView.setImageResource(R.drawable.turkey)
        }
        canadaBtn.setOnClickListener {
            imageView.setImageResource(R.drawable.canada)
        }
    }
}
