package com.example.myfirstproject

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast

class MenusActivity : AppCompatActivity() {

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menus)

        Toast.makeText(this, "Hi", Toast.LENGTH_SHORT).show()
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.first, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.east -> {Toast.makeText(this, "Hi 1", Toast.LENGTH_SHORT).show() }
            R.id.west -> {Toast.makeText(this, "Hi 2", Toast.LENGTH_SHORT).show() }
            R.id.north -> {Toast.makeText(this, "Hi 3", Toast.LENGTH_SHORT).show() }
            R.id.south -> {Toast.makeText(this, "Hi 4", Toast.LENGTH_SHORT).show() }
        }

        return super.onOptionsItemSelected(item)
    }
}
