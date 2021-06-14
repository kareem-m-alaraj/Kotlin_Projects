package com.example.helloworld

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        counterBtn.setOnClickListener {
            val intent = Intent(this, Counter::class.java)
            startActivity(intent)
        }
        frameBtn.setOnClickListener {
            val intent = Intent(this, Frame::class.java)
            startActivity(intent)
        }
        calculatorBtn.setOnClickListener {
            val intent = Intent(this, Calculator::class.java)
            startActivity(intent)
        }
        messagesBtn.setOnClickListener {
            val intent = Intent(this, Messages::class.java)
            startActivity(intent)
        }
        imagesBtn.setOnClickListener {
            val intent = Intent(this, Images::class.java)
            startActivity(intent)
        }
        liveCycleBtn.setOnClickListener {
            val intent = Intent(this, LiveCycle::class.java)
            startActivity(intent)
        }
        twoActivities.setOnClickListener {
            val intent = Intent(this, TwoActivities::class.java)
            startActivity(intent)
        }
        colorsBtn.setOnClickListener {
            val intent = Intent(this, ColorsActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.items, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.counterEx ->{
                Toast.makeText(this, "Counter Example", Toast.LENGTH_SHORT).show()
            }

            R.id.frameEx ->{
                Toast.makeText(this, "Frame Example", Toast.LENGTH_SHORT).show()
            }

            R.id.calculatorEx ->{
                Toast.makeText(this, "Calculator Example", Toast.LENGTH_SHORT).show()
            }

            R.id.messagesEx ->{
                Toast.makeText(this, "Messages Example", Toast.LENGTH_SHORT).show()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}