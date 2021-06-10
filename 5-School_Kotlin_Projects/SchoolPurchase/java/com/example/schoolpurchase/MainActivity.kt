package com.example.schoolpurchase

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

        val namesList = ArrayList<String>()
        val countsList = ArrayList<Double>()
        val pricesList = ArrayList<Double>()

        addDialogBtn.setOnClickListener {

            val inflater = LayoutInflater.from(this)
            val view = inflater.inflate(R.layout.dialog, null)

            val builder = AlertDialog.Builder(this)
            builder.setTitle("إضافة جديد")
            builder.setView(view)

            val dialog = builder.create()
            dialog.show()

            view.addProductBtn.setOnClickListener {
                val name = view.editTextName.text.toString()
                namesList.add(name)

                val count = view.editTextCount.text.toString().toDouble()
                countsList.add(count)

                val price = view.editTextPrice.text.toString().toDouble()
                pricesList.add(price)

                val arrayAdapter = CustomAdapter(this,
                    namesList, countsList, pricesList)

                textViewSum.text = (textViewSum.text.toString().toDouble() + (count * price)).toString()

                listView.adapter = arrayAdapter

                view.editTextName.text = null
                view.editTextCount.text = null
                view.editTextPrice.text = null
                dialog.dismiss()
            }
            view.cancelProductBtn.setOnClickListener {
                dialog.dismiss()
            }
        }
    }
}