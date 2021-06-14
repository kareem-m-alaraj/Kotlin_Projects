package com.example.myapplication

import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.TransitionDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog.*
import kotlinx.android.synthetic.main.dialog.view.*
import kotlin.math.abs


var sum = 0
var sumIn = 0
var sumEx = 0
var inlist:ArrayList<ModelClass> = ArrayList()
var exlist:ArrayList<ModelClass> = ArrayList()
var list:ArrayList<ModelClass> = ArrayList()
var inAdapter: IncomesAdapter? = null
var exAdapter: ExpensesAdapter? = null

class MainActivity : AppCompatActivity() {
    override fun onStart() {
        super.onStart()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val databaseHandler = DatabaseHandler(this)
        inlist = databaseHandler.viewIncomes()
        exlist = databaseHandler.viewOutcomes()
        list = databaseHandler.viewValue()
        sum = 0
        sumIn = 0
        sumEx = 0
        for (i in inlist){
            sumIn += i.Value
        }
        setIncome()
        for (i in exlist){
            sumEx += i.Value
        }
        setExpenses()
        sum = sumIn + sumEx
        setTotal()
        inAdapter = IncomesAdapter(this, inlist)
        exAdapter = ExpensesAdapter(this, exlist, list)

        var flag = false

        tab_layout.setupWithViewPager(view_pager)
        view_pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                if (position == 1){
                    flag = true
                    (frame.background as TransitionDrawable).startTransition(400)
                }else if (flag){
                    (frame.background as TransitionDrawable).resetTransition()
                    (frame.background as TransitionDrawable).reverseTransition(400)
                }
            }

        })

        val view_pagerAdapter = ViewPagerAdapter(supportFragmentManager, 0)
        view_pagerAdapter.addFragment(IncomesFragment(), "Incomes")
        view_pagerAdapter.addFragment(ExpensesFragment(), "Expenses")
        view_pager.adapter = view_pagerAdapter
        IncomesFragment().noIncomes()
        ExpensesFragment().noExpenses()



        fab.setOnClickListener {
            val inflater = LayoutInflater.from(this)
            val view = inflater.inflate(R.layout.dialog, null)
            val dialogBuilder = AlertDialog.Builder(this)
            view.spinner.adapter = (ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,arrayOf("+","-")))
            var flag = false
            var spinnerPosition = 0
            view.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    v: View?,
                    position: Int,
                    id: Long
                ) {
                    spinnerPosition = position
                    if (position == 1){
                        flag = true
                        (view.add_btn.background as TransitionDrawable).startTransition(400)
                    }else if (flag){
                            (view.add_btn.background as TransitionDrawable).resetTransition()
                            (view.add_btn.background as TransitionDrawable).reverseTransition(400)
                    }
                }
            }

            dialogBuilder.setView(view)
            val b = dialogBuilder.create()
            b.window?.setBackgroundDrawableResource(android.R.color.transparent)
            b.show()

            view.add_btn.setOnClickListener {

                if(view.value_text.text.toString().trim().isEmpty() || view.des_text.text.trim().toString().isEmpty() || view.value_text.text.toString().toInt() < 0){
                    Toast.makeText(this,"Enter valid data", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                val id:Int = if(databaseHandler.viewValue().isEmpty()) {
                0
            }else {
                databaseHandler.viewValue()[databaseHandler.viewValue().size - 1].Id + 1
            }
            if (spinnerPosition == 0){
                inlist.add(ModelClass(id, abs((view.value_text.text.toString()).toInt()),view.des_text.text.toString()))
                databaseHandler.addValue(ModelClass(id,
                    abs((view.value_text.text.toString()).toInt()),view.des_text.text.toString()))
                sumIn += abs((view.value_text.text.toString()).toInt())
                sum += abs((view.value_text.text.toString()).toInt())
                setTotal()
                setIncome()
                inAdapter!!.notifyDataSetChanged()
            }else{
                exlist.add(ModelClass(id, -1 * abs((view.value_text.text.toString()).toInt()),view.des_text.text.toString()))
                databaseHandler.addValue(ModelClass(id, -1 * abs((view.value_text.text.toString()).toInt()),view.des_text.text.toString()))
                sumEx += -1 * abs((view.value_text.text.toString()).toInt())
                sum += -1 * abs((view.value_text.text.toString()).toInt())
                setTotal()
                setExpenses()
                exAdapter!!.notifyDataSetChanged()
            }
            if(sumIn > 0){
                exAdapter!!.notifyDataSetChanged()
            }
            IncomesFragment().noIncomes()
            ExpensesFragment().noExpenses()
                b.dismiss()
            }
        }



    }

    fun setIncome() {
        incomesText.text = "$sumIn.00"
    }
    fun setExpenses() {
        expensesText.text = "$sumEx.00"
    }
    fun setTotal() {
        total.text = "$sum.00"
    }

    private class ViewPagerAdapter(
        fm: FragmentManager,
        behavior: Int
    ) :
        FragmentPagerAdapter(fm, behavior) {
        private val fragments: MutableList<Fragment> =
            java.util.ArrayList()
        private val fragmentTitle: MutableList<String> =
            java.util.ArrayList()

        fun addFragment(
            fragment: Fragment,
            title: String
        ) {
            fragments.add(fragment)
            fragmentTitle.add(title)
        }

        override fun getItem(position: Int): Fragment {
            return fragments[position]
        }

        override fun getCount(): Int {
            return fragments.size
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return fragmentTitle[position]
        }
    }


}