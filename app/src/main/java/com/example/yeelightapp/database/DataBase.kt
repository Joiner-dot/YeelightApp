package com.example.yeelightapp.database

import android.annotation.SuppressLint
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import com.example.yeelightapp.database.interfaces.IDataBase
import com.example.yeelightapp.lamps.Lamp

class DataBase(_contex: Context) : IDataBase {
    private val context: Context = _contex
    private val db: SQLiteDatabase = context.openOrCreateDatabase(
        "app.db", MODE_PRIVATE,
        null
    )

    init {
        db.execSQL("CREATE TABLE IF NOT EXISTS lamps (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, name TEXT, ip TEXT)")
    }

    @SuppressLint("Recycle")
    override fun insertNewLamp(vararg values: String) {
        val cursor: Cursor = db.rawQuery("SELECT * FROM lamps WHERE ip = ?", arrayOf(values[1]))
        if (!cursor.moveToFirst()) {
            db.execSQL("INSERT INTO lamps (name, ip) VALUES ('" + values[0] + "', '" + values[1] + "' )")
        }
        else{
            Log.d("TAG", "EXISTS")
        }
    }

    @SuppressLint("Recycle")
    override fun selectAllLamps(): List<Lamp> {
        val cursor: Cursor = db.rawQuery("SELECT * FROM lamps", null)
        val list = arrayListOf<Lamp>()
        if (cursor.moveToFirst()) {
            do {
                list.add(Lamp(cursor.getString(1), cursor.getString(2)))
            } while (cursor.moveToNext())
        }
        return list
    }

}