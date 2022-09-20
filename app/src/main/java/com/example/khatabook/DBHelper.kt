package com.example.khatabook

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context?) : SQLiteOpenHelper(context, "Amount.db", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        var query =
            "CREATE TABLE Amount(id INTEGER PRIMARY KEY AUTOINCREMENT,Amount TEXT,Data TEXT,Date TEXT,status INTEGER,Customer_name TEXT,Customer_num TEXT,Payment TEXT,time TEXT,current TEXT)"
        db!!.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {}

    fun insertData(a1: String, m1: String, d1: String, status: Int, cus_name: String, cus_num: String, pay: String, time: String) {
        var db = writableDatabase

        var cv = ContentValues()
        cv.put("Amount", a1)
        cv.put("Data", m1)
        cv.put("Date", d1)
        cv.put("status", status)
        cv.put("Customer_name", cus_name)
        cv.put("Customer_num", cus_num)
        cv.put("Payment", pay)
        cv.put("time", time)

        var res = db.insert("Amount", null, cv)

        println(res)
    }

    @SuppressLint("Range")
    fun ReadData(): ArrayList<ModelData> {

        var list = arrayListOf<ModelData>()

        var db = readableDatabase
        var query = "SELECT * FROM Amount"

        var cursor = db.rawQuery(query, null)

        if (cursor.moveToFirst()) {
            do {
                var id = cursor.getString(cursor.getColumnIndex("id"))
                var amount = cursor.getString(cursor.getColumnIndex("Amount"))
                var data = cursor.getString(cursor.getColumnIndex("Data"))
                var date = cursor.getString(cursor.getColumnIndex("Date"))
                var statuses = cursor.getString(cursor.getColumnIndex("status"))
                var cus_nama = cursor.getString(cursor.getColumnIndex("Customer_name"))
                var customer_num = cursor.getString(cursor.getColumnIndex("Customer_num"))
                var paym = cursor.getString(cursor.getColumnIndex("Payment"))
                var dattime = cursor.getString(cursor.getColumnIndex("time"))

                var m1 = ModelData(
                    id,
                    amount,
                    data,
                    date,
                    statuses,
                    cus_nama,
                    customer_num,
                    paym,
                    dattime,
                )
                list.add(m1)

            } while (cursor.moveToNext())
        }
        return list
    }

    @SuppressLint("Range")
    fun ReadDataQuery(status: String): ArrayList<ModelData> {

        var list = arrayListOf<ModelData>()

        var db = readableDatabase
        var query = "SELECT * FROM Amount where status = $status"

        var cursor = db.rawQuery(query, null)

        if (cursor.moveToFirst()) {
            do {
                var id = cursor.getString(cursor.getColumnIndex("id"))
                var amount = cursor.getString(cursor.getColumnIndex("Amount"))
                var data = cursor.getString(cursor.getColumnIndex("Data"))
                var date = cursor.getString(cursor.getColumnIndex("Date"))
                var statuses = cursor.getString(cursor.getColumnIndex("status"))
                var cus_nama = cursor.getString(cursor.getColumnIndex("Customer_name"))
                var customer_num = cursor.getString(cursor.getColumnIndex("Customer_num"))
                var paym = cursor.getString(cursor.getColumnIndex("Payment"))
                var dattime = cursor.getString(cursor.getColumnIndex("time"))

                var m1 = ModelData(
                    id,
                    amount,
                    data,
                    date,
                    statuses,
                    cus_nama,
                    customer_num,
                    paym,
                    dattime,
                )
                list.add(m1)

            } while (cursor.moveToNext())
        }
        return list
    }

    @SuppressLint("Range")
    fun ReadDataDate(Date: String): ArrayList<ModelData> {
        var list = arrayListOf<ModelData>()
        var db = readableDatabase
        var query = "SELECT * FROM Amount where Date = $Date"

        var cursor = db.rawQuery(query, null)

        if (cursor.moveToFirst()){

            do {
                var id = cursor.getString(cursor.getColumnIndex("id"))
                var amount = cursor.getString(cursor.getColumnIndex("Amount"))
                var data = cursor.getString(cursor.getColumnIndex("Data"))
                var date = cursor.getString(cursor.getColumnIndex("Date"))
                var statuses = cursor.getString(cursor.getColumnIndex("status"))
                var cus_nama = cursor.getString(cursor.getColumnIndex("Customer_name"))
                var customer_num = cursor.getString(cursor.getColumnIndex("Customer_num"))
                var paym = cursor.getString(cursor.getColumnIndex("Payment"))
                var dattime = cursor.getString(cursor.getColumnIndex("time"))

                var m1 = ModelData(
                    id,
                    amount,
                    data,
                    date,
                    statuses,
                    cus_nama,
                    customer_num,
                    paym,
                    dattime,
                )
                list.add(m1)

            } while (cursor.moveToNext())

        }
        return list

    }

    fun DelateData(id: String) {
        var db = writableDatabase
        db.delete("Amount", "id = $id", null)

    }

    fun UpdateData(id: String, amo: String, data: String, date: String, status: Int, cus_nme: String, cus_no: String, payment: String, time: String
    ) {

        var db = writableDatabase

        var cv = ContentValues()
        cv.put("Amount", amo)
        cv.put("Data", data)
        cv.put("Date", date)
        cv.put("status", status)
        cv.put("Customer_name", cus_nme)
        cv.put("Customer_num", cus_no)
        cv.put("Payment", payment)
        cv.put("time", time)

        db.update("Amount", cv, "id = $id", null)
    }
}