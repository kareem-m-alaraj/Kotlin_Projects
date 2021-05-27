package com.example.sqlproducts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_products.*

class ProductsActivity : AppCompatActivity() {
    lateinit var myDatabase: MyDatabase;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products)

        myDatabase = MyDatabase(this)
        printData()
    }

    fun findProduct(view: View){
        val name = searsh_input.text.toString()
        val dbString = myDatabase.findProduct(name)
        val myAdapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, dbString)
        productsList.adapter = myAdapter;
    }

    private fun printData(){
        val dbString = myDatabase.databaseToString()
        val myAdapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, dbString)
        productsList.adapter = myAdapter
    }
}