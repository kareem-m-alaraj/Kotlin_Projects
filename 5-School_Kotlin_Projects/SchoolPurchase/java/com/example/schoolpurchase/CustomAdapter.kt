package com.example.schoolpurchase

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.list_view_item.view.*

class CustomAdapter(context: Context,
                    val names: ArrayList<out String>,
                    val counts: ArrayList<out Double>,
                    val prices: ArrayList<out Double>) :
    ArrayAdapter<String>(context, R.layout.list_view_item, names) {

    @SuppressLint("ViewHolder")
    override fun getView(postiotn: Int, convertView: View?, parent: ViewGroup): View {

        val l: LayoutInflater = LayoutInflater.from(context)
        val customView:View = l.inflate(R.layout.list_view_item, parent, false)

        customView.textViewListName.text = names!![postiotn]
        customView.textViewListCount.text = counts!![postiotn].toString()
        customView.textViewListPrice.text = prices!![postiotn].toString()

        return customView
    }
}