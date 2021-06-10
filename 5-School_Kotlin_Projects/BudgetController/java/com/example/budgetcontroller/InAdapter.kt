package com.example.budgetcontroller

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.activity_main.view.total
import kotlinx.android.synthetic.main.initem.view.*

class InAdapter(context: Context,
                    private val inlist: ArrayList<ModelClass>
): ArrayAdapter<ModelClass>(context, R.layout.initem,inlist) {



    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val l: LayoutInflater = LayoutInflater.from(context)
        val customView = l.inflate(R.layout.initem, parent, false)

        customView.delete.setOnClickListener {
            sum -= inlist[position].Value
            sumIn -= inlist[position].Value
            (context as MainActivity).setTotal()
            (context as MainActivity).setIncome()

            val databaseHandler: DatabaseHandler = DatabaseHandler(context)
            databaseHandler.deleteValue(inlist[position])
            inlist.removeAt(position)
            outAdapter?.notifyDataSetChanged()
            this.notifyDataSetChanged()
            (context as MainActivity).no()

        }
        customView.inValue.text = inlist[position].Value.toString()
        customView.inDescription.text = inlist[position].Des
        return customView
    }
}
