package com.example.unit2project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        first_fragment.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment, BlankFragment())
                .commit()
        }
        second_fragment.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment, BlankFragment2())
                .commit()
        }
    }
}