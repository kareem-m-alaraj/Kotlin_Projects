package com.example.studentslist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog.view.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val stdNames = ArrayList<String>()
        val stdAddresses = ArrayList<String>()

        addDialogBtn.setOnClickListener {
            val inflater = LayoutInflater.from(this)
            val view = inflater.inflate(R.layout.dialog, null)

            val builder = AlertDialog.Builder(this)
            builder.setTitle("إضافة جديد")
            builder.setView(view)

            val dialog = builder.create()
            dialog.show()

            view.addStdBtn.setOnClickListener {
                val name = view.editTextName.text.toString()
                stdNames.add(name)

                val address = view.editTextAddress.text.toString()
                stdAddresses.add(address)

                val arrayAdapter = CustomAdapter(this,
                    stdNames, stdAddresses)

                listView.adapter = arrayAdapter

                textViewStdCount.text = stdNames.size.toString()

                view.editTextName.text = null
                view.editTextAddress.text = null
                dialog.dismiss()
            }

            view.cancelAddStdBtn.setOnClickListener {
                dialog.dismiss()
            }
        }
    }
}
