package com.example.customgridview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.item.view.*

class CustomAdapter(context: Context,
                    private val names:ArrayList<String>,
                    private val images:ArrayList<Int>)
    :ArrayAdapter<String>(context, R.layout.item, names) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val layoutInflater:LayoutInflater = LayoutInflater.from(context)
        val custom_view : View = layoutInflater.inflate(R.layout.item, parent, false)

        custom_view.textView.text = names[position!!]
        custom_view.imageView.setImageResource(images[position!!])

        return custom_view
    }
}