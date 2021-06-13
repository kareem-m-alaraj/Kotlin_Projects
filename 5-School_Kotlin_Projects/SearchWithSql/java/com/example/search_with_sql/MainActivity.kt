package com.example.myapplication

import android.app.AlertDialog
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.*
import android.widget.AbsListView.OnScrollListener.SCROLL_STATE_IDLE
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList


var string = ""
var names = arrayListOf<String>()
var ids = arrayListOf<Int>()

var lv: ListView? = null


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lv = findViewById(R.id.listView)
        val databaseHandler: DatabaseHandler = DatabaseHandler(this)
        val list: List<ModelClass> = databaseHandler.viewContact()

        for ((i, e) in list.withIndex()) {
            names.add(e.Name)
            ids.add(e.Id)
        }
        var myAdapter = CustomAdapter(this, names)
        listView.adapter = myAdapter






        editText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                string = s.toString()
                names.clear()
                ids.clear()
                for(i in databaseHandler.searchContact(string)){
                    names.add(i.Name)
                    ids.add(i.Id)
                }
                myAdapter = CustomAdapter(this@MainActivity, names)
                listView.adapter = myAdapter

        }})

        s_btn.setOnClickListener {
            this.currentFocus?.let { view ->
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
                imm?.hideSoftInputFromWindow(view.windowToken, 0)
                editText.clearFocus() }
        }

        listView.setOnScrollListener(object : AbsListView.OnScrollListener {
            override fun onScroll(
                view: AbsListView?,
                firstVisibleItem: Int,
                visibleItemCount: Int,
                totalItemCount: Int
            ) {
            }

            override fun onScrollStateChanged(view: AbsListView?, scrollState: Int) {
                if(scrollState != SCROLL_STATE_IDLE){
                    myAdapter.setScrollanim(true)
                }
                val first = view?.firstVisiblePosition
                val count = view?.childCount
                if (scrollState == SCROLL_STATE_IDLE || first!! + count!! > myAdapter.count) {
                    myAdapter.setScrollanim(false)
                }
            }
        })

        add_btn.setOnClickListener {
            val contacts = databaseHandler.viewContact()
            val id:Int
            id = if(contacts.isEmpty()) {
                0
            }else {
                contacts[contacts.size-1].Id + 1
            }
            if (name.text.trim()!="" ){
                val status = databaseHandler.addContact(ModelClass(id,name.text.toString()))
                if(status > -1){
                    Toast.makeText(applicationContext,"record save", Toast.LENGTH_LONG).show()
                    names.add(name.text.toString())
                    ids.add(id)
                    name.setText("")
                    myAdapter.notifyDataSetChanged()
                    name.text.clear()
                }
            }else{
                Toast.makeText(applicationContext,"id or name or email cannot be blank",Toast.LENGTH_LONG).show()
            }


            listView.smoothScrollToPosition(names.size-1)
            this.currentFocus?.let { view ->
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
                imm?.hideSoftInputFromWindow(view.windowToken, 0)
            }
        }

        listView.onItemLongClickListener = AdapterView.OnItemLongClickListener { arg0, arg1, pos, id ->
            val dialogBuilder = AlertDialog.Builder(this)
            val input =  EditText(this)
            input.setText(names[pos])
            dialogBuilder.setTitle("Update Record")
            dialogBuilder.setMessage("Update ${names[pos]} record?")
            dialogBuilder.setView(input)
            dialogBuilder.setPositiveButton("Update") { _, _ ->
                databaseHandler.updateContact(ModelClass(ids[pos], input.text.toString()))
                names[pos] = input.text.toString()
                myAdapter.notifyDataSetChanged()
                name.clearFocus()
                notify(13456,"Updating record","Record has been updated")
            }
            dialogBuilder.setNegativeButton("Cancel") { _, _ ->
            }
            val b = dialogBuilder.create()
            b.show()

            true
        }
    }
    fun notify(id:Int, title: String,text: String){
        val CHANNEL_ID = "channel_01"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val mChannel = NotificationChannel(CHANNEL_ID, "اسم القناة", importance)
        var notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(title)
            .setContentText(text)
            .build()
        val mNotificationManager= getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        mNotificationManager.createNotificationChannel(mChannel)
        mNotificationManager.notify(id, notification)

    }

}
