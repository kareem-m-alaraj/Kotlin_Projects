package com.example.studentslist

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.list_view_item.view.*

class CustomAdapter(context: Context,
                    val names: ArrayList<out String>,
                    val addresses: ArrayList<out String>) :
    ArrayAdapter<String>(context, R.layout.list_view_item, names) {

    @SuppressLint("ViewHolder")
    override fun getView(postiotn: Int, convertView: View?, parent: ViewGroup): View {

        val l:LayoutInflater = LayoutInflater.from(context)
        val customView:View = l.inflate(R.layout.list_view_item, parent, false)

        customView.textViewStdName.text = names!![postiotn]
        customView.textViewStdAddress.text = addresses!![postiotn]
        customView.textViewSN.text = (postiotn + 1).toString()
        return customView
    }

}