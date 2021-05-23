package com.example.simplegridview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val names = arrayOf("Kareem", "Mohammed", "Alaraj")

        val array_adapter:ArrayAdapter<String> = ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, names)

        gridView.adapter = array_adapter

        gridView.setOnItemClickListener { adapterView, view, i, l ->
            val n = adapterView.getItemAtPosition(i).toString()
            Toast.makeText(this, n, Toast.LENGTH_SHORT).show()
        }
    }
}