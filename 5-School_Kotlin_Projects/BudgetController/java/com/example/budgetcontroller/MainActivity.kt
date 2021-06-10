package com.example.budgetcontroller

import android.annotation.SuppressLint
import android.graphics.drawable.TransitionDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.add_btn
import kotlinx.android.synthetic.main.activity_main.des_text
import kotlinx.android.synthetic.main.activity_main.inList
import kotlinx.android.synthetic.main.activity_main.outList
import kotlinx.android.synthetic.main.activity_main.spinner
import kotlinx.android.synthetic.main.activity_main.value_text
import kotlinx.android.synthetic.main.activity_main1.*
import kotlin.math.abs

var sum = 0
var sumIn = 0
var sumOut = 0

var inlist:ArrayList<ModelClass> = ArrayList()
var outlist:ArrayList<ModelClass> = ArrayList()
var list:ArrayList<ModelClass> = ArrayList()

var inAdapter: InAdapter? = null
var outAdapter: OutAdapter? = null


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main1)

        var x = arrayListOf("+","-")
        spinner.adapter = (ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,x))
        var flag = false
        var y = 0

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                y = position
                val drawable = add_btn.background as TransitionDrawable
                val drawable1 = frame.background as TransitionDrawable
                if (position == 1){
                    flag = true
                    drawable.startTransition(400)
                    drawable1.startTransition(400)
                }else if (flag){
                    if (flag){
                        drawable.resetTransition()
                        drawable.reverseTransition(400)
                        drawable1.resetTransition()
                        drawable1.reverseTransition(400)
                    }
                }
            }
        }

        val databaseHandler: DatabaseHandler = DatabaseHandler(this)
         inlist = databaseHandler.viewIncomes()
         outlist = databaseHandler.viewOutcomes()
         list = databaseHandler.viewValue()

        for (i in inlist){
            sumIn += i.Value
        }
        for (i in outlist){
            sumOut += i.Value
        }
        for (i in list){
            sum += i.Value
        }
        if(inlist.size == 0){
            noIn.visibility = View.VISIBLE
            inList.visibility = View.GONE
        }else{
            noIn.visibility = View.GONE
            inList.visibility = View.VISIBLE
        }
        setTotal()
        setIncome()
        setOutcome()

         inAdapter = InAdapter(this, inlist)
        inList.adapter = inAdapter
         outAdapter = OutAdapter(this, outlist, list)
        outList.adapter = outAdapter

        add_btn.setOnClickListener {
            if(value_text.text.toString().trim().isEmpty() || des_text.text.trim().toString().isEmpty() || value_text.text.toString().toInt() < 0){
                Toast.makeText(this,"Enter a valid data",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val id:Int = if(databaseHandler.viewValue().isEmpty()) {
                0
            }else {
                databaseHandler.viewValue()[databaseHandler.viewValue().size - 1].Id + 1
            }
            if (y == 0){
                inlist.add(ModelClass(id, abs((value_text.text.toString()).toInt()),des_text.text.toString()))
                databaseHandler.addValue(ModelClass(id,abs((value_text.text.toString()).toInt()),des_text.text.toString()))
                sumIn += abs((value_text.text.toString()).toInt())
                sum += abs((value_text.text.toString()).toInt())
                setTotal()
                setIncome()
                inAdapter!!.notifyDataSetChanged()
            }else{
                outlist.add(ModelClass(id, -1 * abs((value_text.text.toString()).toInt()),des_text.text.toString()))
                databaseHandler.addValue(ModelClass(id, -1 * abs((value_text.text.toString()).toInt()),des_text.text.toString()))
                sumOut += -1 * abs((value_text.text.toString()).toInt())
                sum += -1 * abs((value_text.text.toString()).toInt())
                setTotal()
                setOutcome()
                outAdapter!!.notifyDataSetChanged()
            }
            if(sumIn > 0){
                outAdapter!!.notifyDataSetChanged()
            }
            des_text.text.clear()
            value_text.text.clear()
            no()
        }


    }
    fun no(){
        if(inlist.size == 0){
            noIn.visibility = View.VISIBLE
            inList.visibility = View.GONE
        }else{
            noIn.visibility = View.GONE
            inList.visibility = View.VISIBLE
        }
        if(outlist.size == 0){
            noOut.visibility = View.VISIBLE
            outList.visibility = View.GONE
        }else{
            noOut.visibility = View.GONE
            outList.visibility = View.VISIBLE
        }
    }

    fun setIncome() {
        textView5.text = "$sumIn.00"
    }
    fun setOutcome() {
        textView6.text = "$sumOut.00"
    }
    fun setTotal() {
        total.text = "$sum.00"
    }
}
