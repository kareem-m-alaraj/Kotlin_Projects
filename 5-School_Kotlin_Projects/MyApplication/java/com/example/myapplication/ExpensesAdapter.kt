package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.ex_item.view.*
import kotlin.math.abs



var exLastPosition = -1

class ExpensesAdapter(context: Context,
                      private val exlist: ArrayList<ModelClass>,
                      private val list:ArrayList<ModelClass>,
                      private var scrollAnim: Boolean = false

): ArrayAdapter<ModelClass>(context, R.layout.ex_item,exlist) {
    fun setScrollanim(boolean: Boolean){
        scrollAnim = boolean
    }
    @SuppressLint("SetTextI18n")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val l: LayoutInflater = LayoutInflater.from(context)
        val customView = l.inflate(R.layout.ex_item, parent, false)

        if (scrollAnim) {
            val animation = AnimationUtils.loadAnimation(
                context,
                if (position > inLastPosition) R.anim.up_from_bottom else R.anim.down_from_top
            )
            customView.startAnimation(animation)
            exLastPosition = position
        }
        if (position % 2 == 0) {
            customView.setBackgroundResource(R.drawable.light )
        }


        customView.exDeleteBtn.setOnClickListener {
            sum -= exlist[position].Value
            sumEx -= exlist[position].Value
            (context as MainActivity).setTotal()
            (context as MainActivity).setExpenses()
            val databaseHandler = DatabaseHandler(context)
            databaseHandler.deleteValue(exlist[position])
            exlist.removeAt(position)
            ExpensesFragment().noExpenses()
            this.notifyDataSetChanged()
            
        }
        customView.exValue.text = exlist[position].Value.toString()
        customView.exDescription.text = exlist[position].Des
        if (sumIn>0){
            customView.percent.visibility = View.VISIBLE
            customView.percent.text = "${(((abs(exlist[position].Value)) / sumIn.toDouble()) * 100).toInt()}%"
        }else{
            customView.percent.visibility = View.GONE
        }
        return customView
    }
}

