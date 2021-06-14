package com.example.myapplication

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_incomes.view.*

var inFragmentView: View? = null

class IncomesFragment : Fragment() {

    fun noIncomes(): Boolean {
        return if(inlist.size == 0){
            inFragmentView?.noIn?.visibility = View.VISIBLE
            inFragmentView?.inList?.visibility  = View.GONE
            true

        }else{
            inFragmentView?.noIn?.visibility = View.GONE
            inFragmentView?.inList?.visibility   = View.VISIBLE
            false
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_incomes, container, false)
        view.inList.adapter = inAdapter
        view.inList.setOnScrollListener(object : AbsListView.OnScrollListener {
            override fun onScroll(
                view: AbsListView?,
                firstVisibleItem: Int,
                visibleItemCount: Int,
                totalItemCount: Int
            ) {
            }

            override fun onScrollStateChanged(view: AbsListView?, scrollState: Int) {
                if(scrollState != AbsListView.OnScrollListener.SCROLL_STATE_IDLE){
                    inAdapter?.setScrollanim(true)
                }
                val first = view?.firstVisiblePosition
                val count = view?.childCount
                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE || first!! + count!! > inAdapter?.count!!) {
                    inAdapter?.setScrollanim(false)
                }
            }
        })

        inFragmentView = view
        noIncomes()
        return inFragmentView
    }



}
