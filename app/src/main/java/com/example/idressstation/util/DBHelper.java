package com.example.idressstation.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper( Context context) {
        super(context, "IdressStation.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table Profits(ID INTEGER PRIMARY KEY AUTOINCREMENT, Amount int , Type Text, PurDate Text,  FOREIGN KEY(Type) REFERENCES Types(Type)  )");
        db.execSQL("create Table Types(Type text primary key, Amount int, whprice int, price int) ");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }

    public boolean insertRow(int amount, String type, String date){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();

        contentValues.put("Amount", amount);
        contentValues.put("Type", type);
        contentValues.put("PurDate", date);

        long result = db.insert("Profits", null, contentValues );

        if(result == -1){
            return false;
        }
        else{
            return true;
        }




    }


    public void read() {

        SQLiteDatabase sqlData = this.getWritableDatabase();

        Cursor cursor = sqlData.rawQuery("Select * from Profits",null);

        while (cursor.moveToNext()){

            Log.d("Data", cursor.getString(3) );


        }


    }
}
