package com.example.customgridview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val names = arrayListOf<String>("FaceBook", "Instagram", "WhatsApp")
        val images = arrayListOf<Int>(R.drawable.facebook, R.drawable.instagram, R.drawable.whatsapp)

        val myAdapter:CustomAdapter = CustomAdapter(this, names, images)
        gridView.adapter = myAdapter;

        gridView.setOnItemClickListener { adapterView, view, i, l ->
            val n = adapterView.getItemAtPosition(i).toString()
            Toast.makeText(this, n, Toast.LENGTH_SHORT).show()
        }
        gridView.onItemLongClickListener = AdapterView.OnItemLongClickListener { parent, view, position, id ->
            names.removeAt(position)
            images.removeAt(position)
            myAdapter.notifyDataSetChanged()
            true
        }
    }
}