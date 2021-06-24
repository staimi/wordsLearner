package com.example.wordslearner

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Build
import androidx.annotation.RequiresApi
import java.util.*

class data_saver (context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABAS_VER) {

    companion object{
        private val DATABAS_VER = 1
        private val DATABASE_NAME = "DATA.db"

        // table

        private val TABLE_NAME = "Words_data"
        private val COL_WORD1 = "Deutsch"
        private val COL_WORD2 = "Polski"

    }

    override fun onCreate(p0: SQLiteDatabase?) {

        val CREATE_TABLE_QUERY: String = ("CREATE TABLE " + TABLE_NAME+" ("
                + COL_WORD1 + " TEXT, "
                + COL_WORD2 + " TEXT)")
        p0!!.execSQL(CREATE_TABLE_QUERY)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0!!.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(p0!!)
    }

    val allList:List<example_item_data>
        @RequiresApi(Build.VERSION_CODES.O)
        get() {
            val fwords = ArrayList<example_item_data>()
            val selectQuery = "SELECT * FROM $TABLE_NAME"
            val p0:SQLiteDatabase = this.writableDatabase
            val cursor:Cursor = p0.rawQuery(selectQuery, null)

            if (cursor.moveToFirst()){
                do{
                    val wordst = example_item_data()
                    wordst.WORD1 = cursor.getString(cursor.getColumnIndex(COL_WORD1))
                    wordst.WORD2 = cursor.getString(cursor.getColumnIndex(COL_WORD2))

                    fwords.add(wordst)
                }while (cursor.moveToNext())
            }
            p0.close()
            return fwords

        }

    fun addWeight(fwords: example_item_data){

        val p0:SQLiteDatabase = this.writableDatabase
        val values = ContentValues()

        values.put(COL_WORD1, fwords.WORD1)
        values.put(COL_WORD2, fwords.WORD2)

        p0.insert(TABLE_NAME, null, values)
        p0.close()
    }

    fun updateWeight(fwords: example_item_data):Int
    {
        val p0:SQLiteDatabase = this.writableDatabase
        val values = ContentValues()

        values.put(COL_WORD1, fwords.WORD1)
        values.put(COL_WORD2, fwords.WORD2)

        return p0.update(TABLE_NAME,values, "$COL_WORD1", arrayOf(fwords.WORD1))
    }

    fun deleteWeight(fwords: example_item_data)
    {
        val p0:SQLiteDatabase = this.writableDatabase

        p0.delete(TABLE_NAME, "$COL_WORD1", arrayOf(fwords.WORD1))
        p0.close()
    }

    fun dropTable(){
        // drop table
        val p0:SQLiteDatabase = this.writableDatabase
        p0.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
    }
}
