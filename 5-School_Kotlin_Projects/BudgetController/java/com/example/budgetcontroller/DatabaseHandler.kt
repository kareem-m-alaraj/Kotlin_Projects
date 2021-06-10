package com.example.budgetcontroller

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper

class ModelClass(
    var Id: Int,
    var Value:Int,
    var Des: String
)
class DatabaseHandler(context: Context): SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION) {
    companion object {
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "valueDatabase"
        private val TABLE_BADGET = "valueTable"
        private val KEY_ID = "id"
        private val KEY_VALUE = "value"
        private val KEY_DES = "des"
    }
    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_BADGET_TABLE = ("CREATE TABLE " + TABLE_BADGET + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_VALUE + " INTEGER,"
                + KEY_DES + " TEXT"
                + ")"
                )
        db?.execSQL(CREATE_BADGET_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        //  TODO("not implemented") //To change body of created functions use File | Settings | File Tschlates.
        db!!.execSQL("DROP TABLE IF EXISTS " + TABLE_BADGET)
        onCreate(db)
    }


    fun addValue(modelClass: ModelClass):Long{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_ID, modelClass.Id)
        contentValues.put(KEY_VALUE, modelClass.Value)
        contentValues.put(KEY_DES, modelClass.Des)
        val success = db.insert(TABLE_BADGET, null, contentValues)
        db.close()
        return success
    }
    fun viewValue():ArrayList<ModelClass>{
        val valueList = ArrayList<ModelClass>()
        val selectQuery = "SELECT  * FROM $TABLE_BADGET"
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
                    cursor.getInt(cursor.getColumnIndex(KEY_VALUE)),
                    cursor.getString(cursor.getColumnIndex(KEY_DES)))
                valueList.add(modelClass)
            } while (cursor.moveToNext())
        }
        return valueList
    }


    fun viewIncomes():ArrayList<ModelClass>{
        val valueList = ArrayList<ModelClass>()
        val selectQuery =
            "SELECT * FROM valueTable \n" +
                    "WHERE value > 0"
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
                    cursor.getInt(cursor.getColumnIndex(KEY_VALUE)),
                    cursor.getString(cursor.getColumnIndex(KEY_DES)))
                valueList.add(modelClass)
            } while (cursor.moveToNext())
        }
        return valueList
    }
    fun viewOutcomes():ArrayList<ModelClass>{
        val valueList = ArrayList<ModelClass>()
        val selectQuery = "SELECT * FROM valueTable \n" +
                "WHERE value < 0"

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
                    cursor.getInt(cursor.getColumnIndex(KEY_VALUE)),
                    cursor.getString(cursor.getColumnIndex(KEY_DES)))
                valueList.add(modelClass)
            } while (cursor.moveToNext())
        }
        return valueList
    }

    fun deleteValue(modelClass: ModelClass):Int{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_ID, modelClass.Id)
        val success = db.delete(TABLE_BADGET,"id="+modelClass.Id,null)
        db.close()
        return success
    }
}
