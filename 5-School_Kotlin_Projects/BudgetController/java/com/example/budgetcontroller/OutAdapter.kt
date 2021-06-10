package com.example.budgetcontroller

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.activity_main.view.total
import kotlinx.android.synthetic.main.outitem.view.*
import kotlin.math.abs


class OutAdapter(context: Context,
                 private val outlist: ArrayList<ModelClass>,
                 private val list:ArrayList<ModelClass>
): ArrayAdapter<ModelClass>(context, R.layout.outitem,outlist) {

    @SuppressLint("SetTextI18n")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val l: LayoutInflater = LayoutInflater.from(context)
        val customView = l.inflate(R.layout.outitem, parent, false)


        customView.delete.setOnClickListener {
            sum -= outlist[position].Value
            sumOut -= outlist[position].Value
            (context as MainActivity).setTotal()
            (context as MainActivity).setOutcome()
            val databaseHandler: DatabaseHandler = DatabaseHandler(context)
            databaseHandler.deleteValue(outlist[position])
            outlist.removeAt(position)
            this.notifyDataSetChanged()
            (context as MainActivity).no()
        }
        customView.outValue.text = outlist[position].Value.toString()
        customView.outDescription.text = outlist[position].Des
        if (sumIn>0){
            customView.pircent.visibility = View.VISIBLE
            customView.pircent.text = "${(((abs(outlist[position].Value)) / sumIn.toDouble()) * 100).toInt()}%"
        }else{
            customView.pircent.visibility = View.GONE
        }
        return customView
    }
}
