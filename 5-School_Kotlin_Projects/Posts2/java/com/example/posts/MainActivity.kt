package com.example.posts

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

        val postArrayId = ArrayList<Int>()
        val postArrayTitle = ArrayList<String>()
        val postArrayDescription = ArrayList<String>()

        for(obj in posts){
            postArrayId.add(obj.Id)
            postArrayTitle.add(obj.Title)
            postArrayDescription.add(obj.Description)
        }

        //creating custom ArrayAdapter
        val postAdapter = PostAdapter(this, postArrayId, postArrayTitle, postArrayDescription)

        listView.adapter = postAdapter
    }
}
