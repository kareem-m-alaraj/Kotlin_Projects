package com.example.fragmentlifecycle

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

/**
 * A simple [Fragment] subclass.
 * Use the [BlankFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BlankFragment : Fragment() {
    // TODO: Rename and change types of parameters

    override fun onAttach(context: Context) {
        Toast.makeText(activity, "onAttach", Toast.LENGTH_SHORT).show()
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Toast.makeText(activity, "onCreate", Toast.LENGTH_SHORT).show()
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        Toast.makeText(activity, "onActivityCreated", Toast.LENGTH_SHORT).show()
        super.onActivityCreated(savedInstanceState)
    }

    override fun onStart() {
        Toast.makeText(activity, "onStart", Toast.LENGTH_SHORT).show()
        super.onStart()
    }

    override fun onResume() {
        Toast.makeText(activity, "onResume", Toast.LENGTH_SHORT).show()
        super.onResume()
    }

    override fun onPause() {
        Toast.makeText(activity, "onPause", Toast.LENGTH_SHORT).show()
        super.onPause()
    }

    override fun onStop() {
        Toast.makeText(activity, "onStop", Toast.LENGTH_SHORT).show()
        super.onStop()
    }

    override fun onDestroyView() {
        Toast.makeText(activity, "onDestroyView", Toast.LENGTH_SHORT).show()
        super.onDestroyView()
    }

    override fun onDestroy() {
        Toast.makeText(activity, "onDestroy", Toast.LENGTH_SHORT).show()
        super.onDestroy()
    }

    override fun onDetach() {
        Toast.makeText(activity, "onDetach", Toast.LENGTH_SHORT).show()
        super.onDetach()
    }

}