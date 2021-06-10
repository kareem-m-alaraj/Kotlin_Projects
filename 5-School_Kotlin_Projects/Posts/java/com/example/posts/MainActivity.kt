package com.example.posts

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addBtnActiviy.setOnClickListener {
            val intent = Intent(this, AddPostActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()

        //creating the instance of PostHelper class
        val postHelper = PostsHelper(this)

        //calling the viewPosts method of PostsHelper class to read the records
        val posts: List<PostsModel> = postHelper.viewPosts()

        val postArrayId = Array<Int>(posts.size){0}
        val postArrayTitle = Array<String>(posts.size){""}
        val postArrayDescription = Array<String>(posts.size){""}

        var index = 0
        for(obj in posts){
            postArrayId[index] = obj.Id
            postArrayTitle[index] = obj.Title
            postArrayDescription[index] = obj.Description
            index++
        }

        //creating custom ArrayAdapter
        val postAdapter = PostAdapter(this, postArrayId,postArrayTitle,postArrayDescription)

        listView.adapter = postAdapter
    }
}
