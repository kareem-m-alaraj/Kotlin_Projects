package com.example.sqlblog

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ListView
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.add_dialog.view.*
import kotlinx.android.synthetic.main.search_dialog.view.*

var myDatabase: MyDatabase? = null;
var blog: ArrayList<BlogTemplate>? = null;
var myBlogList: ListView? = null;
var myAdapter: CustomAdapter? = null;

@Suppress("LABEL_NAME_CLASH")
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myDatabase = MyDatabase(this);
        blog = myDatabase!!.getBlog();
        myBlogList = blogList;
        myAdapter = CustomAdapter(this, blog!!);
        blogList.adapter = myAdapter;

        // Add Blog
        addBlogButton.setOnClickListener {
            val layoutInflater = LayoutInflater.from(this);
            val view = layoutInflater.inflate(R.layout.add_dialog, null);

            val dialogBuilder = AlertDialog.Builder(this);
            dialogBuilder.setView(view);

            val dialog = dialogBuilder.create();
            dialog.show();

            view.addOkayButton.setOnClickListener {
                if (view.addTitleInput.text.toString() == "" || view.addDescriptionInput.text.toString() == "")
                    return@setOnClickListener;

                val blogTitle = view.addTitleInput.text.toString();
                val blogDescription =  view.addDescriptionInput.text.toString();
                val blogTemplate = BlogTemplate(0, blogTitle, blogDescription);
                myDatabase!!.addBlog(blogTemplate);
                blog = myDatabase!!.getBlog();
                myAdapter = CustomAdapter(this, blog!!);
                blogList.adapter = myAdapter;

                dialog.cancel();
            }

            view.addCancelButton.setOnClickListener {
                dialog.cancel();
            }
        }

        // Search Blog
        searchBlogButton.setOnClickListener {
            val layoutInflater = LayoutInflater.from(this);
            val view = layoutInflater.inflate(R.layout.search_dialog, null);

            val dialogBuilder = AlertDialog.Builder(this);
            dialogBuilder.setView(view);

            val dialog = dialogBuilder.create();
            dialog.show();

            view.searchOkayButton.setOnClickListener {
                if (view.searchTitleInput.text.toString() == "") return@setOnClickListener;

                val blogTitle = view.searchTitleInput.text.toString();
                blog = myDatabase!!.searchBlog(blogTitle);
                myAdapter = CustomAdapter(this, blog!!);
                blogList.adapter = myAdapter;

                dialog.cancel();
            }

            view.searchCancelButton.setOnClickListener {
                dialog.cancel();
            }
        }

        // All Blogs
        allBlogButton.setOnClickListener {
            myDatabase = MyDatabase(this);
            blog = myDatabase!!.getBlog();
            myBlogList = blogList;
            myAdapter = CustomAdapter(this, blog!!);
            blogList.adapter = myAdapter;
        }
    }
}

// Delete Blog
fun deleteBlog (context: Context) {
    blog = myDatabase!!.getBlog();
    myAdapter = CustomAdapter(context, blog!!);
    myBlogList!!.adapter = myAdapter;
}

// Update Blog
fun updateBlog (context: Context) {
    blog = myDatabase!!.getBlog();
    myAdapter = CustomAdapter(context, blog!!);
    myBlogList!!.adapter = myAdapter;
}