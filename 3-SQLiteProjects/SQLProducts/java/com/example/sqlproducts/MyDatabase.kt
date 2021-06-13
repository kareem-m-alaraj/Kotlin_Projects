package com.example.sqlproducts

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyDatabase(context: Context) : SQLiteOpenHelper(context, MyDatabase.DATABASE_NAME, null, MyDatabase.DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL(DATABASE_CREATE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $DATABASE_NAME");
        onCreate(db)
    }

    companion object {
        val KEY_ID = "ID"
        val PRODUCT_NAME = "NAME"
        val PRODUCT_QUANTITY = "QUANTITY"
        val DATABASE_TABLE_NAME = "products"
        private val DATABASE_NAME = "data.db"
        private val DATABASE_VERSION = 1
        private val DATABASE_CREATE =
                "CREATE TABLE $DATABASE_TABLE_NAME " +
                "($KEY_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$PRODUCT_NAME TEXT NOT NULL, " +
                "$PRODUCT_QUANTITY INTEGER NOT NULL);"
    }

    fun addProduct (productName:String, name:String, productQuantity:String, quantity:Int){
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(productName, name)
        values.put(productQuantity, quantity)

        db.insert(DATABASE_TABLE_NAME, null, values)
        db.close();
    }

    fun deleteProduct (productID: Int){
        val db = this.writableDatabase
        db.execSQL("DELETE FROM $DATABASE_TABLE_NAME WHERE $KEY_ID = \"$productID\";")
    }

    fun findProduct (productName: String): Array<String> {
        var dbString: Array<String>
        val db = this.writableDatabase
        val query = "SELECT * FROM $DATABASE_TABLE_NAME WHERE $PRODUCT_NAME Like \"$productName\";"
        val cursor = db.rawQuery(query, null)

        dbString = Array<String>(cursor.count){""};
        cursor.moveToFirst();

        while (!cursor.isAfterLast){
            for (index in dbString.indices){
                if (
                        cursor.getString(cursor.getColumnIndex(KEY_ID)) != null &&
                        cursor.getString(cursor.getColumnIndex(PRODUCT_NAME)) != null &&
                        cursor.getString(cursor.getColumnIndex(PRODUCT_QUANTITY)) != null
                ){
                    dbString[index] +=
                            "ID: ${cursor.getString(cursor.getColumnIndex(KEY_ID))} \n" +
                            "Name: ${cursor.getString(cursor.getColumnIndex(PRODUCT_NAME))} \n" +
                            "Quantity: ${cursor.getString(cursor.getColumnIndex(PRODUCT_QUANTITY))} \n";
                }
                cursor.moveToNext()
            }
        }
        cursor.close()
        return dbString;
    }

    fun databaseToString () :Array<String> {
        var dbString: Array<String>
        val db = this.writableDatabase
        val query = "SELECT * FROM $DATABASE_TABLE_NAME"
        val cursor = db.rawQuery(query, null)

        dbString = Array<String>(cursor.count){""}
        cursor.moveToFirst();


        while (!cursor.isAfterLast){
            for (index in dbString.indices){
                if (
                        cursor.getString(cursor.getColumnIndex(KEY_ID)) != null &&
                        cursor.getString(cursor.getColumnIndex(PRODUCT_NAME)) != null &&
                        cursor.getString(cursor.getColumnIndex(PRODUCT_QUANTITY)) != null
                ) {
                    dbString[index] +=
                            "ID: ${cursor.getString(cursor.getColumnIndex(KEY_ID))} \n" +
                            "Name: ${cursor.getString(cursor.getColumnIndex(PRODUCT_NAME))} \n" +
                            "Quantity: ${cursor.getString(cursor.getColumnIndex(PRODUCT_QUANTITY))} \n";
                }
                cursor.moveToNext()
            }
        }
        cursor.close()
        return dbString;
    }
}