package com.example.myfirstproject

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        firstActBtn.setOnClickListener {
            val intent = Intent(this, FirstActivity::class.java)
            startActivity(intent)
        }
        secondActBtn.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }
        menusActBtn.setOnClickListener {
            val intent = Intent(this, MenusActivity::class.java)
            startActivity(intent)
        }
        numberActBtn.setOnClickListener {
            val intent = Intent(this, numper::class.java)
            startActivity(intent)
        }
        imagesActBtn.setOnClickListener {
            val intent = Intent(this, ImagesActivity::class.java)
            startActivity(intent)
        }
    }
}