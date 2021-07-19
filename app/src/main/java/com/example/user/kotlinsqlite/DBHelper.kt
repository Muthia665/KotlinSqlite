package com.example.user.kotlinsqlite

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context):SQLiteOpenHelper(context,DATABASE_NAME, null, DATABASE_VER) {
    companion object {
        private val DATABASE_NAME = "siswi.db"
        private val DATABASE_VER = 1

        // table
        private val TABLE_NAME = "siswi"

        // table column
        private val COL_ID = "id"
        private val COL_NAME = "name"
        private val COL_EMAIL = "email"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE_QUERY: String =
            ("CREATE TABLE $TABLE_NAME ($COL_ID INTEGER PRIMARY KEY, $COL_NAME TEXT, $COL_EMAIL TEXT)")
        db!!.execSQL(CREATE_TABLE_QUERY)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db!!) // dibikin yang
    }

    //CRUD

    //read
    val allsiswi: List<Siswi>
        get() {
            val listsiswi = ArrayList<Siswi>()
            val selectQuery = "SELECT * FROM $TABLE_NAME"

            val db = this.writableDatabase
            val cursor = db.rawQuery(selectQuery, null)
            if (cursor.moveToFirst()) {
                do {
                    val siswi = Siswi()
                    siswi.id = cursor.getInt(cursor.getColumnIndex(COL_ID))
                    siswi.name = cursor.getString(cursor.getColumnIndex(COL_NAME))
                    siswi.email = cursor.getString(cursor.getColumnIndex(COL_EMAIL))

                    listsiswi.add(siswi)
                } while (cursor.moveToNext())
            }
            db.close()
            return listsiswi
        }

    //create
    fun addsiswi(siswi: Siswi) {
        val db = this.writableDatabase

        val values = ContentValues()
        values.put(COL_ID, siswi.id)
        values.put(COL_NAME, siswi.name)
        values.put(COL_EMAIL, siswi.email)

        // menambahkan data baru
        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    // update
    fun updatesiswi(siswi: Siswi): Int {
        val db = this.writableDatabase

        val values = ContentValues()
        values.put(COL_ID, siswi.id)
        values.put(COL_NAME, siswi.name)
        values.put(COL_EMAIL, siswi.email)

        return db.update(TABLE_NAME, values, "$COL_ID=?", arrayOf(siswi.id.toString()))
    }

    // delelte
    fun deletesiswi(siswi: Siswi){
        val db = this.writableDatabase

        db.delete(TABLE_NAME, "$COL_ID=?", arrayOf(siswi.id.toString()))
        db.close()
    }
}