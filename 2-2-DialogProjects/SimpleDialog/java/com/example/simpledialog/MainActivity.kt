package com.example.simpledialog

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        button.setOnClickListener{
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Are You OK?")
            var dialog = builder.create()
            builder.setPositiveButton("Yes"){
                _: DialogInterface, _: Int ->
                dialog.dismiss()
            }
            builder.setNegativeButton("NO"){
                _: DialogInterface, _: Int ->
            }
            dialog = builder.create()
            dialog.show()
        }

    }
}