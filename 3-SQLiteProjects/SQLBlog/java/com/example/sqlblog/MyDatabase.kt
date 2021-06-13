package com.example.sqlblog

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper

class MyDatabase(context: Context): SQLiteOpenHelper(context, MyDatabase.DATABASE_NAME, null, MyDatabase.DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL(DATABASE_CREATE);
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $DATABASE_NAME;");
    }

    companion object{
        val BLOG_ID = "ID";
        val BLOG_TITLE = "TITLE";
        val BLOG_DESCRIPTION  = "DESCRIPTION";
        val DATABASE_TABLE_NAME = "BLOG";
        private val DATABASE_NAME = "data.db";
        private val DATABASE_VERSION = 1
        private val DATABASE_CREATE =
            "CREATE TABLE IF NOT EXISTS $DATABASE_TABLE_NAME " +
                    "($BLOG_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "$BLOG_TITLE TEXT NOT NULL, " +
                    "$BLOG_DESCRIPTION TEXT NOT NULL);";
    }

    fun addBlog (blogTemplate: BlogTemplate){
        val db = this.writableDatabase;
        val values = ContentValues();
        values.put(BLOG_TITLE, blogTemplate.title);
        values.put(BLOG_DESCRIPTION, blogTemplate.description);

        db.insert(DATABASE_TABLE_NAME, null, values);
        db.close();
    }

    fun updateBlog(blogTemplate: BlogTemplate){
        val db = this.writableDatabase;
        val values = ContentValues();
        values.put(BLOG_TITLE, blogTemplate.title);
        values.put(BLOG_DESCRIPTION, blogTemplate.description);

        db.update(DATABASE_TABLE_NAME, values, "$BLOG_ID = \"${blogTemplate.id}\";", null);
        db.close()
    }

    fun deleteBlog(blogID: Int){
        val db = this.writableDatabase;
        db.execSQL("DELETE FROM $DATABASE_TABLE_NAME WHERE $BLOG_ID = \"$blogID\";");
    }

    fun getBlog(): ArrayList<BlogTemplate> {
        var blogList = ArrayList<BlogTemplate>();
        val db = this.writableDatabase
        val query = "SELECT * FROM $DATABASE_TABLE_NAME;";
        var cursor: Cursor? = null;

        try {
            cursor = db.rawQuery(query, null);
        }catch (error: SQLiteException){
            db.execSQL(query);
            return ArrayList();
        }

        if (cursor.moveToFirst()){
            do {
                val blogID = cursor.getInt(cursor.getColumnIndex(BLOG_ID));
                val blogTitle = cursor.getString(cursor.getColumnIndex(BLOG_TITLE));
                val blogDescription = cursor.getString(cursor.getColumnIndex(BLOG_DESCRIPTION));
                val blog = BlogTemplate(blogID, blogTitle, blogDescription);
                blogList.add(blog);
            }while (cursor.moveToNext())
        }
        return blogList;
    }

    fun searchBlog(blogTitle: String): ArrayList<BlogTemplate>{
        var blogList = ArrayList<BlogTemplate>();
        val query = "SELECT * FROM $DATABASE_TABLE_NAME WHERE $BLOG_TITLE Like '%${blogTitle}%'";
        val db = this.readableDatabase;
        var cursor: Cursor? = null;

        try{
            cursor = db.rawQuery(query, null)
        }catch (error: SQLiteException) {
            db.execSQL(query)
            return ArrayList()
        }

        if (cursor.moveToFirst()) {
            do {
                val blogID = cursor.getInt(cursor.getColumnIndex(BLOG_ID));
                val blogTitle = cursor.getString(cursor.getColumnIndex(BLOG_TITLE));
                val blogDescription = cursor.getString(cursor.getColumnIndex(BLOG_DESCRIPTION));
                val blog = BlogTemplate(blogID, blogTitle, blogDescription);
                blogList.add(blog)
            } while (cursor.moveToNext())
        }
        return blogList;
    }
}