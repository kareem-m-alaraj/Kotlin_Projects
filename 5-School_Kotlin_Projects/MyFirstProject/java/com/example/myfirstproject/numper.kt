package com.example.myfirstproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_numper.*

class numper : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_numper)

        nextBtn.setOnClickListener {
            val intent = Intent (this, number2::class.java)
            var n1 = Num1.text.toString()
            var n2 = Num2.text.toString()
            intent.putExtra("n1", n1)
            intent.putExtra("n2", n2)
            startActivity(intent)
        }
    }
}
