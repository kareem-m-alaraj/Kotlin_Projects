package com.example.customlistviewwithadditem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val names = arrayListOf<String>()
        val images = arrayListOf<Int>()
        val myAdapter:CustomAdapter = CustomAdapter(this, names, images)
        listView.adapter = myAdapter;

        addBtn.setOnClickListener{
            if (editText.text.toString() == "") return@setOnClickListener

            val name = editText.text.toString();
            names.add(name);
            images.add(R.drawable.instagram)
            myAdapter.notifyDataSetChanged()

            editText.text = null;

        }

        listView.setOnItemClickListener { adapterView, view, i, l ->
            val n = adapterView.getItemAtPosition(i).toString()
            Toast.makeText(this, n, Toast.LENGTH_SHORT).show()
        }
    }
}