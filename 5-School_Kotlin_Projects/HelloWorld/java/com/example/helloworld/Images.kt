package com.example.helloworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_calculator.*
import kotlinx.android.synthetic.main.activity_images.*

class Images : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_images)

        val images = arrayOf(R.drawable.palestine, R.drawable.jordan, R.drawable.turkey)
        val names = arrayOf("فلسطين", "الاردن", "تركيا")
        var x = 0

        nextBtn.setOnClickListener {
            imageView.setImageResource(images[x])
            nameView.setText(names[x])
            x = (x + 1) % 3
        }
    }
}