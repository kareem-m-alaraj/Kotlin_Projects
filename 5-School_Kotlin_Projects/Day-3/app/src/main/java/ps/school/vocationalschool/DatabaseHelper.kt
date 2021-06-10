package ps.school.vocationalschool

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper

class SchoolModel(
    var id: Int,
    var name: String,
    var address: String,
    var phone: Int,
    var number: Int
)

class DatabaseHandler(context: Context): SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION) {
    companion object {
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "school_db"
        private val TABLE_NAME = "school"
        private val KEY_ID = "id"
        private val KEY_NAME = "name"
        private val KEY_ADDRESS = "address"
        private val KEY_PHONE = "phone"
        private val KEY_NUMBER = "number"
    }
    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE = ("CREATE TABLE " + TABLE_NAME + "("
                + KEY_ID +  " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_NAME +  " TEXT,"
                + KEY_ADDRESS +  " TEXT,"
                + KEY_PHONE +  " INTEGER,"
                + KEY_NUMBER +  " INTEGER"
                + ")"
                )
        db?.execSQL(CREATE_TABLE)
    }
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }
    fun addSchool(schoolModel: SchoolModel):Long{
        val db = this.writableDatabase
        val contentValues = ContentValues()

        contentValues.put(KEY_NAME, schoolModel.name)
        contentValues.put(KEY_ADDRESS, schoolModel.address)
        contentValues.put(KEY_PHONE, schoolModel.phone)
        contentValues.put(KEY_NUMBER, schoolModel.number)

        val success = db.insert(TABLE_NAME, null, contentValues)
        db.close()
        return success
    }
    
    fun viewSchool():ArrayList<SchoolModel>{
        val list = ArrayList<SchoolModel>()
        val selectQuery = "SELECT  * FROM $TABLE_NAME"
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
                val schoolModel = SchoolModel(
                    cursor.getInt(cursor.getColumnIndex(KEY_ID)),
                    cursor.getString(cursor.getColumnIndex(KEY_NAME)),
                    cursor.getString(cursor.getColumnIndex(KEY_ADDRESS)),
                    cursor.getInt(cursor.getColumnIndex(KEY_PHONE)),
                    cursor.getInt(cursor.getColumnIndex(KEY_NUMBER))
                )
                list.add(schoolModel)
            } while (cursor.moveToNext())
        }
        return list
    }

    fun deleteSchool(id: Int):Int{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_ID, id)
        val success = db.delete(TABLE_NAME,"$KEY_ID="+ id,null)
        db.close()
        return success
    }
}