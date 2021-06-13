package com.example.myapplication

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.item.view.*
import java.util.*
import kotlin.collections.ArrayList


var lastPosition = -1


class CustomAdapter(
    context: MainActivity,
    var FilterResults: ArrayList<String>,
    var scrollAnim:Boolean = false

) : ArrayAdapter<String>(context, R.layout.item, FilterResults) {



    fun setName(arrayList: ArrayList<String>){
        FilterResults = arrayList
    }
    fun setScrollanim(boolean: Boolean){
        scrollAnim = boolean
    }
    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val l: LayoutInflater = LayoutInflater.from(context)
        val customView = l.inflate(R.layout.item, parent, false)




        if (scrollAnim){
            val animation = AnimationUtils.loadAnimation(
                context,
                if (position > lastPosition) R.anim.up_from_bottom else R.anim.down_from_top
            )
            customView!!.startAnimation(animation)
            lastPosition = position
        }

        if (position % 2 == 1) {
            customView.setBackgroundResource(R.drawable.light)}

        customView.btn.setOnClickListener {
            val dialogBuilder = AlertDialog.Builder(context)
            dialogBuilder.setTitle("Delete Record")
            dialogBuilder.setMessage("Do you want to delete ${FilterResults[position]} record?")
            dialogBuilder.setPositiveButton("Delete", DialogInterface.OnClickListener { _, _ ->
                val databaseHandler: DatabaseHandler= DatabaseHandler(context)
                Log.wtf("@@@", FilterId[position].toString())
                Log.wtf("@@@", position.toString())
                var x = databaseHandler.viewContact()[FilterId[position]].Id

                databaseHandler.deleteContact(ModelClass(x,""))
                names.removeAt(FilterId[position])
                FilterResults.removeAt(position)
                (context as MainActivity).filter(string)
                this.notifyDataSetChanged()
                (context as MainActivity).notify(12456,"Deleting record","Record has been deleted")
            })
            dialogBuilder.setNegativeButton("Cancel", DialogInterface.OnClickListener { _, _ ->
                //pass
            })
            val b = dialogBuilder.create()
            b.show()

        }
        customView.textView.text = FilterResults[position]
        return customView
    }


}


