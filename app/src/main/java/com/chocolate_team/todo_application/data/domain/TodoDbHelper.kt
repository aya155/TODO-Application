package com.chocolate_team.todo_application.data.domain

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.chocolate_team.todo_application.util.Constant

class TodoDbHelper(context: Context):SQLiteOpenHelper(context,DB_NAME,null,VERSION) {

    override fun onCreate(database: SQLiteDatabase) {
        val sql="CREATE TABLE ${Constant.TABLE_NAME} (" +
                "${Constant.ID} INTEGER PRIMARY KEY ," +
                "${Constant.TITLE} TEXT ," +
                "${Constant.DUE_TIME} TEXT," +
                "${Constant.DUE_DATE} TEXT," +
                "${Constant.REMIND} BOOLEAN " +
                ")"
        database.execSQL(sql)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }
    companion object{
        const val DB_NAME="Todo"
        const val VERSION=1
    }

}