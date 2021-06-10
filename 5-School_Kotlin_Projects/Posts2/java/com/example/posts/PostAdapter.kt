package com.example.posts

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.list_item.view.*

class PostAdapter(private val context: Activity,
                  private val id: ArrayList<Int>,
                  private val title: ArrayList<String>,
                  private val description: ArrayList<String>)
    : ArrayAdapter<String>(context, R.layout.list_item, title) {

    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.list_item, null, true)

        //val idText = rowView.findViewById(R.id.textViewId) as TextView
        val nameText = rowView.findViewById(R.id.textViewTitle) as TextView
        val emailText = rowView.findViewById(R.id.textViewDescription) as TextView

        //idText.text = "Id: ${id[position]}"
        nameText.text = "${title[position]}"
        emailText.text = "${description[position]}"

        rowView.imageButtonEdit.setOnClickListener {

            /**************** Start Update Dialog ****************/
            val dialogBuilder = AlertDialog.Builder(getContext())

            val inflater = LayoutInflater.from(parent.context)

            val dialogView = inflater.inflate(R.layout.update_dialog, null)
            dialogBuilder.setView(dialogView)

            val editTitle = dialogView.findViewById(R.id.updateTitle) as EditText
            val editDescription = dialogView.findViewById(R.id.updateDescription) as EditText

            editTitle.setText(title[position])
            editDescription.setText(description[position])

            dialogBuilder.setTitle("Update Post")
            dialogBuilder.setMessage("Update data below")

            dialogBuilder.setPositiveButton("Update", DialogInterface.OnClickListener { _, _ ->

                val updateTitle = editTitle.text.toString()
                val updateDescription = editDescription.text.toString()

                //creating the instance of PostsHelper class
                val postsHelper = PostsHelper(getContext())
                if(updateTitle.trim()!="" && updateDescription.trim()!=""){
                    //calling the updatePost method of PostsHelper class to update record
                    var postObj = PostsModel(id[position], updateTitle, updateDescription)
                    val status = postsHelper.updatePost(postObj)
                    if(status > -1){
                        title[position] = updateTitle
                        description[position] = updateDescription
                        notifyDataSetChanged()
                        Toast.makeText(getContext(),"record updated", Toast.LENGTH_LONG).show()
                    }
                }else{
                    Toast.makeText(getContext(),"id or name or email cannot be blank",Toast.LENGTH_LONG).show()
                }
            })
            dialogBuilder.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which ->
                //pass
            })
            val b = dialogBuilder.create()
            b.show()

            /**************** End Update Dialog ****************/
        }

        rowView.imageButtonDelete.setOnClickListener {

            /**************** Start Delete Dialog ****************/

            //creating AlertDialog for taking user id
            val dialogBuilder = AlertDialog.Builder(getContext())
            val inflater = LayoutInflater.from(parent.context)
            val dialogView = inflater.inflate(R.layout.delete_dialog, null)

            dialogBuilder.setView(dialogView)

            val dltTtlte = dialogView.findViewById(R.id.deleteTiltle) as TextView

            dltTtlte.text = title[position]
            dialogBuilder.setTitle("Delete Record")
            dialogBuilder.setMessage("Delete record below")

            dialogBuilder.setPositiveButton("Delete", DialogInterface.OnClickListener { _, _ ->
                val postsHelper = PostsHelper(getContext())

                val post = PostsModel(id[position],"","")
                val status = postsHelper.deletePost(post)

                if(status > -1){
                    id.removeAt(position)
                    title.removeAt(position)
                    description.removeAt(position)
                    notifyDataSetChanged()
                    Toast.makeText(getContext(),"record deleted", Toast.LENGTH_LONG).show()
                }

            })
            dialogBuilder.setNegativeButton("Cancel", DialogInterface.OnClickListener { _, _ ->
                //pass
            })
            val b = dialogBuilder.create()
            b.show()

            /**************** End Delete Dialog ****************/
        }
        return rowView
    }
}