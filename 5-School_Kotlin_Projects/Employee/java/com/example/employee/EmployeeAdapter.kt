package com.example.employee

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.employee.R
import kotlinx.android.synthetic.main.add_edit_layout.view.*
import kotlinx.android.synthetic.main.custom_view.view.*

class EmployeeAdapter(context: Context,
                      private val names: ArrayList<String>) :
    ArrayAdapter<String>(context, R.layout.custom_view, names) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val l: LayoutInflater = LayoutInflater.from(context)
        val customView = l.inflate(R.layout.custom_view, parent, false)

        customView.textViewItem.text= names[position]

        customView.imageButtonDel.setOnClickListener {
            names.removeAt(position)
        }

        customView.imageButtonEdit.setOnClickListener {

        }

        return customView
    }
}