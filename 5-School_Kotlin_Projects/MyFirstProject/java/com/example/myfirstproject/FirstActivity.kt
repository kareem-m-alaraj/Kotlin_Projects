package com.example.myfirstproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_first.*
import kotlinx.android.synthetic.main.activity_main.*

class FirstActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)

        addBtn.setOnClickListener {
            var x = n1.text.toString().toDouble()
            var y = n2.text.toString().toDouble()
            var z = x + y
            output.text = z.toString()
        }

        minBtn.setOnClickListener {
            var x = n1.text.toString().toDouble()
            var y = n2.text.toString().toDouble()
            var z = x - y
            output.text = z.toString()
        }

        mulBtn.setOnClickListener {
            var x = n1.text.toString().toDouble()
            var y = n2.text.toString().toDouble()
            var z = x * y
            output.text = z.toString()
        }

        divBtn.setOnClickListener {
            var x = n1.text.toString().toDouble()
            var y = n2.text.toString().toDouble()
            var z = x / y
            output.text = z.toString()
        }

        colorBtn.setOnClickListener {
            var intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }
    }
}