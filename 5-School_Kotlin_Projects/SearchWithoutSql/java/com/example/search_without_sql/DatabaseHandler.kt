package com.example.myapplication

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteException

//creating the database logic, extending the SQLiteOpenHelper base class  
class DatabaseHandler(context: Context): SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION) {
    companion object {
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "contactDatabase"
        private val TABLE_CONTACTS = "contactTable"
        private val KEY_ID = "id"
        private val KEY_NAME = "name"
//        private val KEY_NUM = "num"
    }
    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_CONTACTS_TABLE = ("CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_NAME + " TEXT"
//                + KEY_NUM + " TEXT"
                + ")"
                )
        db?.execSQL(CREATE_CONTACTS_TABLE)
        val contentValues = ContentValues()
        contentValues.put(KEY_ID, "0")
        contentValues.put(KEY_NAME, "Alaa Breim")
//        contentValues.put(KEY_NUM,"0598980337")
        db?.insert(TABLE_CONTACTS, null, contentValues)
        contentValues.put(KEY_ID, "1")
        contentValues.put(KEY_NAME, "Shawqi")
//        contentValues.put(KEY_NUM,"0598980337")
        db?.insert(TABLE_CONTACTS, null, contentValues)
        contentValues.put(KEY_ID, "2")
        contentValues.put(KEY_NAME, "Kareem")
//        contentValues.put(KEY_NUM,"0598980337")
        db?.insert(TABLE_CONTACTS, null, contentValues)
        contentValues.put(KEY_ID, "3")
        contentValues.put(KEY_NAME, "Khalid")
//        contentValues.put(KEY_NUM,"0598980337")
        db?.insert(TABLE_CONTACTS, null, contentValues)
        contentValues.put(KEY_ID, "4")
        contentValues.put(KEY_NAME, "Mohammad")
//        contentValues.put(KEY_NUM,"0598980337")
        db?.insert(TABLE_CONTACTS, null, contentValues)
        contentValues.put(KEY_ID, "5")
        contentValues.put(KEY_NAME, "Hosam")
//        contentValues.put(KEY_NUM,"0598980337")
        db?.insert(TABLE_CONTACTS, null, contentValues)
        contentValues.put(KEY_ID, "6")
        contentValues.put(KEY_NAME, "Khalel")
//        contentValues.put(KEY_NUM,"0598980337")
        db?.insert(TABLE_CONTACTS, null, contentValues)
        contentValues.put(KEY_ID, "7")
        contentValues.put(KEY_NAME, "Adnan")
//        contentValues.put(KEY_NUM,"0598980337")
        db?.insert(TABLE_CONTACTS, null, contentValues)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        //  TODO("not implemented") //To change body of created functions use File | Settings | File Tschlates.
        db!!.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS)
        onCreate(db)
    }


    fun addContact(modelClass: ModelClass):Long{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_ID, modelClass.Id)
        contentValues.put(KEY_NAME, modelClass.Name)
//        contentValues.put(KEY_NUM, modelClass.Num)
        val success = db.insert(TABLE_CONTACTS, null, contentValues)
        db.close()
        return success
    }
    fun viewContact():List<ModelClass>{
        val contactList = ArrayList<ModelClass>()
        val selectQuery = "SELECT  * FROM $TABLE_CONTACTS"
        val db = this.readableDatabase
        var cursor: Cursor? = null
        try{
            cursor = db.rawQuery(selectQuery, null)
        }catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }

        if (cursor.moveToFirst()) {
            do {

                val modelClass = ModelClass(cursor.getInt(cursor.getColumnIndex(KEY_ID)),
                    cursor.getString(cursor.getColumnIndex(KEY_NAME))
//                    , cursor.getString(cursor.getColumnIndex(KEY_NUM))
                )
                contactList.add(modelClass)
            } while (cursor.moveToNext())
        }
        return contactList
    }


    fun updateContact(modelClass: ModelClass):Int{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_ID, modelClass.Id)
        contentValues.put(KEY_NAME, modelClass.Name)
//        contentValues.put(KEY_NUM, modelClass.Num)
        val success = db.update(TABLE_CONTACTS, contentValues,"id="+modelClass.Id,null)
        db.close()
        return success
    }

    fun deleteContact(modelClass: ModelClass):Int{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_ID, modelClass.Id)
        val success = db.delete(TABLE_CONTACTS,"id="+modelClass.Id,null)
        db.close()
        return success
    }
}  
