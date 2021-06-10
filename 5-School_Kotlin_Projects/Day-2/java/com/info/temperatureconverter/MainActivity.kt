package com.info.temperatureconverter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment, AboutFragment())
            .commit()

        aboutBtn.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment, AboutFragment())
                .commit()
        }

        calculateBtn.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment, ConvertFragment())
                .commit()
        }
    }
}
