package com.example.unit2project

import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.fragment_blank.*
import kotlinx.android.synthetic.main.fragment_blank.view.*

class BlankFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_blank, container, false)

        view.square_Area_Btn.setOnClickListener {
            val n1 = view.num1.text.toString().toDouble()
//            val n2 = view.num2.text.toString().toDouble()
            val area = n1 * n1
//            view.square_Area_View.text = area.toString()

            val builder = AlertDialog.Builder(activity!!)
            builder.setTitle("Square Area when length : ${n1.toString()}")
            builder.setMessage("= ${area.toString()}")
            builder.setPositiveButton("OK") {
                    _: DialogInterface, _: Int ->
                val dialog = builder.create()
                dialog.dismiss()
            }
            val dialog = builder.create()
            dialog.show()

            num1.text = null

        }
        return view
    }
}