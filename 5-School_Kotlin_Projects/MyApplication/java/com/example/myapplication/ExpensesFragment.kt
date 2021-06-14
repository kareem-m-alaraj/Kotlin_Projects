package com.example.myapplication

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_expenses.view.*
import kotlinx.android.synthetic.main.fragment_incomes.view.*

var exFragmentView: View? = null

class ExpensesFragment : Fragment() {

    fun noExpenses(): Boolean {
        return if(exlist.size == 0){
            exFragmentView?.noEx?.visibility = View.VISIBLE
            exFragmentView?.exList?.visibility = View.GONE
            true
        }else{
            exFragmentView?.noEx?.visibility = View.GONE
            exFragmentView?.exList?.visibility = View.VISIBLE
            false

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_expenses, container, false)
        view.exList.adapter = exAdapter
        view.exList.setOnScrollListener(object : AbsListView.OnScrollListener {
            override fun onScroll(
                view: AbsListView?,
                firstVisibleItem: Int,
                visibleItemCount: Int,
                totalItemCount: Int
            ) {
            }

            override fun onScrollStateChanged(view: AbsListView?, scrollState: Int) {
                if(scrollState != AbsListView.OnScrollListener.SCROLL_STATE_IDLE){
                    exAdapter?.setScrollanim(true)
                }
                val first = view?.firstVisiblePosition
                val count = view?.childCount
                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE || first!! + count!! > exAdapter?.count!!) {
                    exAdapter?.setScrollanim(false)
                }
            }
        })

        exFragmentView = view
        noExpenses()
        return exFragmentView
    }


}
