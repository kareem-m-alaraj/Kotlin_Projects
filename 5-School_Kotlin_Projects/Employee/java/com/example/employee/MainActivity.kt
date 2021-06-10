package com.example.employee

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.add_edit_layout.*
import kotlinx.android.synthetic.main.add_edit_layout.view.*
import kotlinx.android.synthetic.main.add_edit_layout.view.editText
import kotlinx.android.synthetic.main.custom_view.*
import kotlinx.android.synthetic.main.custom_view.view.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnAdd.setOnClickListener {

            val inflater = LayoutInflater.from(this)
            val view = inflater.inflate(R.layout.add_edit_layout, null)


            val builder = AlertDialog.Builder(this)
            builder.setView(view)

            val dialog = builder.create()
            dialog.show()

            view.btnSave.setOnClickListener {

                if (view.editText.text.toString() == ""
                    || view.editText2.text.toString() == ""
                    || view.editText3.text.toString() == ""
                    || view.editText4.text.toString() == ""
                    || view.editText5.text.toString() == ""){

                    Toast.makeText(this, "can't save", Toast.LENGTH_SHORT).show()
                    dialog.cancel()
                }else{
                    val names = arrayListOf<String>()
                    names.add(view.editText.text.toString())
                    val myAdapter = EmployeeAdapter(this, names)
                    listViewEmployee.adapter = myAdapter
                    myAdapter.notifyDataSetChanged()
                    dialog.cancel()
                }

            }
            view.btnCancel.setOnClickListener {
                dialog.cancel()
            }
        }


//        listViewEmployee.setOnItemClickListener { parent: AdapterView<*>?, view: View?, position: Int, id: Long ->
//            val x = parent?.getItemAtPosition(position).toString()
//            Toast.makeText(this, x, Toast.LENGTH_SHORT).show()
//        }
    }
}