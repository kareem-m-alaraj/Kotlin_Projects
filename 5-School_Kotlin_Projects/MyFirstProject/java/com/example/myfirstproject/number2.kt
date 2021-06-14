package com.example.myfirstproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_number2.*

class number2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_number2)

        val intent = getIntent()
        view1.text = intent.getStringExtra("n1")
        view2.text = intent.getStringExtra("n2")

    }
}
