package com.example.posts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_post.*

class AddPostActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_post)

        addBtn.setOnClickListener {

            val title = editTextTitle.text.toString()
            val description = editTextDescription.text.toString()

            val postHelper = PostsHelper(this)

            if(title.trim()!="" && description.trim()!="" ){

                val status = postHelper.addPost(PostsModel(0, title, description))
                if(status > -1){
                    Toast.makeText(applicationContext,"record added", Toast.LENGTH_LONG).show()
                }
            }else{
                Toast.makeText(applicationContext,"title or description cannot be blank", Toast.LENGTH_LONG).show()
            }
            finish();
        }
    }
}
