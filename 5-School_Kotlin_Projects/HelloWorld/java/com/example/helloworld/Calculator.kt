package com.example.helloworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_calculator.*

class Calculator : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)



        addBtn.setOnClickListener {
            var x = num1.text.toString().toDouble()
            var y = num2.text.toString().toDouble()
            var z = x + y
            textView.text = z.toString()
        }
        minBtn.setOnClickListener {
            var x = num1.text.toString().toDouble()
            var y = num2.text.toString().toDouble()
            var z = x - y
            textView.text = z.toString()
        }
        mulBtn.setOnClickListener {
            var x = num1.text.toString().toDouble()
            var y = num2.text.toString().toDouble()
            var z = x * y
            textView.text = z.toString()
        }
        divBtn.setOnClickListener {
            var x = num1.text.toString().toDouble()
            var y = num2.text.toString().toDouble()
            var z = x / y
            textView.text = z.toString()
        }
    }
}