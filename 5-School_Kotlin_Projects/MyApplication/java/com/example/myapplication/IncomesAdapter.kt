package com.example.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.in_item.view.*

var inLastPosition = -1

class IncomesAdapter(context: Context,
    private val inlist: ArrayList<ModelClass>,
    private var scrollAnim: Boolean = false

): ArrayAdapter<ModelClass>(context, R.layout.in_item,inlist) {

    fun setScrollanim(boolean: Boolean){
        scrollAnim = boolean
    }
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val l: LayoutInflater = LayoutInflater.from(context)
        val customView = l.inflate(R.layout.in_item, parent, false)
        if (scrollAnim) {
            val animation = AnimationUtils.loadAnimation(
                context,
                if (position > inLastPosition) R.anim.up_from_bottom else R.anim.down_from_top
            )
            customView!!.startAnimation(animation)
            inLastPosition = position
        }
        if (position % 2 == 1) {
            customView.setBackgroundResource(R.drawable.light )
        }


        customView.inDeleteBtn.setOnClickListener {
            sum -= inlist[position].Value
            sumIn -= inlist[position].Value
            (context as MainActivity).setTotal()
            (context as MainActivity).setIncome()

            val databaseHandler = DatabaseHandler(context)
            databaseHandler.deleteValue(inlist[position])
            inlist.removeAt(position)
            IncomesFragment().noIncomes()
            exAdapter?.notifyDataSetChanged()
            this.notifyDataSetChanged()
        }
        customView.inValue.text = inlist[position].Value.toString()
        customView.inDescription.text = inlist[position].Des
        return customView
    }



}
