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
val FilterResults = arrayListOf<String>()
var names = arrayListOf<String>()
var FilterId = arrayListOf<Int>()

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
            FilterResults.add(e.Name)
            FilterId.add(i)
        }


        var myAdapter = CustomAdapter(this, FilterResults)
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
                filter(string)
                myAdapter.notifyDataSetChanged()
            }
        })

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

            val databaseHandler: DatabaseHandler= DatabaseHandler(this)
            val contacts = databaseHandler.viewContact()
            var id:Int
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
                FilterResults.add(name.text.toString())
                filter(string)
                name.setText("")
                myAdapter.notifyDataSetChanged()
                name.text.clear()
            }
        }else{
            Toast.makeText(applicationContext,"id or name or email cannot be blank",Toast.LENGTH_LONG).show()
        }


            listView.smoothScrollToPosition(FilterResults.size-1)
            this.currentFocus?.let { view ->
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
                imm?.hideSoftInputFromWindow(view.windowToken, 0)
            }
        }

        listView.onItemLongClickListener = AdapterView.OnItemLongClickListener { arg0, arg1, pos, id ->
            val dialogBuilder = AlertDialog.Builder(this)
            val input =  EditText(this)
            input.setText(FilterResults[pos])
            dialogBuilder.setTitle("Update Record")
            dialogBuilder.setMessage("Update ${FilterResults[pos]} record?")
            dialogBuilder.setView(input)
            dialogBuilder.setPositiveButton("Update") { _, _ ->
                val databaseHandler: DatabaseHandler= DatabaseHandler(this)
                var x =databaseHandler.viewContact()[FilterId[pos]].Id
                databaseHandler.updateContact(ModelClass(x, input.text.toString()))
                FilterResults[pos] = input.text.toString()
                names[FilterId[pos]] = input.text.toString()
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
    fun filter(charText: String) {
        var charText = charText
        charText = charText.toLowerCase(Locale.getDefault())
        FilterResults.clear()
        FilterId.clear()
        if (charText.isEmpty()) {
            FilterResults.addAll(names)
            for(i in 0 until names.size){
                FilterId.add(i)
            }
        } else {
            for (i in 0 until names.size) {
                if (names[i].toLowerCase(Locale.getDefault()).contains(charText)) {
                    FilterResults.add(names[i])
                    FilterId.add(i)
                }
            }
        }
    }

}