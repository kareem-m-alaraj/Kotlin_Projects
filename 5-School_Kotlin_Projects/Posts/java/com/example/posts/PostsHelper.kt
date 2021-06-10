package com.example.posts

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.icu.text.CaseMap


class PostsModel(var Id: Int,
                 val Title:String ,
                 val Description: String);

class PostsHelper (context: Context): SQLiteOpenHelper(context, DATABASE_NAME,null,DATABASE_VERSION){
    companion object {
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "PostsDB"
        private val TABLE = "posts"
        private val KEY_ID = "id"
        private val KEY_TITLE = "title"
        private val KEY_DESCRIPTION = "description"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        //creating table with fields
        val CREATE_TABLE_STATMENT = ("CREATE TABLE "
                + TABLE + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_TITLE + " VARCHAR(200),"
                + KEY_DESCRIPTION + " TEXT" + ")")
        db?.execSQL(CREATE_TABLE_STATMENT)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS " + TABLE)
        onCreate(db)
    }

    //method to insert data
    fun addPost(post: PostsModel):Long{
        val db = this.writableDatabase

        val contentValues = ContentValues()

        contentValues.put(KEY_TITLE, post.Title)
        contentValues.put(KEY_DESCRIPTION, post.Description)

        // Inserting Row
        val success = db.insert(TABLE, null, contentValues)

        //2nd argument is String containing nullColumnHack
        db.close() // Closing database connection
        return success
    }

    //method to read data
    fun viewPosts():List<PostsModel>{

        val postList:ArrayList<PostsModel> = ArrayList<PostsModel>()
        val selectQuery = "SELECT * FROM $TABLE"
        val db = this.readableDatabase
        var cursor: Cursor? = null
        try{
            cursor = db.rawQuery(selectQuery, null)
        }catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }

        var Id: Int
        var Title: String
        var Description: String

        if (cursor.moveToFirst()) {
            do {
                Id = cursor.getInt(cursor.getColumnIndex("id"))
                Title = cursor.getString(cursor.getColumnIndex("title"))
                Description = cursor.getString(cursor.getColumnIndex("description"))
                val emp= PostsModel(Id = Id, Title = Title, Description = Description)
                postList.add(emp)
            } while (cursor.moveToNext())
        }
        return postList
    }

    //method to update data
    fun updatePost(post: PostsModel):Int{
        val db = this.writableDatabase
        val contentValues = ContentValues()

        contentValues.put(KEY_ID, post.Id)
        contentValues.put(KEY_TITLE, post.Title)
        contentValues.put(KEY_DESCRIPTION,post.Description)

        // Updating Row
        val success = db.update(TABLE, contentValues,"id=" + post.Id,null)

        //2nd argument is String containing nullColumnHack
        db.close() // Closing database connection
        return success
    }

    //method to delete data
    fun deletePost(post: PostsModel):Int{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_ID, post.Id)

        // Deleting Row
        val success = db.delete(TABLE,"id=" + post.Id,null)

        //2nd argument is String containing nullColumnHack
        db.close() // Closing database connection
        return success
    }
}