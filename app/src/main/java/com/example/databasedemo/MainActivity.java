package com.example.databasedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Create Database
        SQLiteDatabase myDataBase=this.openOrCreateDatabase("Users",MODE_PRIVATE,null);

        //Create Table
        myDataBase.execSQL("CREATE TABLE IF NOT EXISTS users (name VARCHAR,age INT(3))");
        myDataBase.execSQL("CREATE TABLE IF NOT EXISTS users2 (id INT(3) PRIMARY KEY,name VARCHAR,age INT(3))");
        //primary key if not specified values increases by 1, Eg 1,2,3,4....

        //Insert Values
        //myDataBase.execSQL("INSERT INTO users (name,age) VALUES ('Nikhil',21)");
        //myDataBase.execSQL("INSERT INTO users (name,age) VALUES ('Nikhil Khurana',28)");

        String s="Niks";
        int a=24;
        //myDataBase.execSQL("INSERT INTO users (name,age) VALUES ('"+s+"',"+a+")");

        //Take data
        Cursor cursor=myDataBase.rawQuery("SELECT * FROM users",null);
        Cursor cursor2=myDataBase.rawQuery("SELECT * FROM users WHERE age>23",null);
        Cursor cursor3=myDataBase.rawQuery("SELECT * FROM users WHERE name='Nikhil' AND age=21",null);

        //Start with 'N'
        Cursor cursor4=myDataBase.rawQuery("SELECT * FROM users WHERE name LIKE 'N%'",null);

        //Has 'a' in name
        Cursor cursor5=myDataBase.rawQuery("SELECT * FROM users WHERE name LIKE '%a%'",null);

        //Has 'a' in name and only first name
        Cursor cursor6=myDataBase.rawQuery("SELECT * FROM users WHERE name LIKE '%a%' LIMIT 1",null);

        int nameIndex= cursor.getColumnIndex("name");
        int ageIndex=cursor.getColumnIndex("age");

        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            Log.i("Name...Age", cursor.getString(nameIndex)+"..."+Integer.toString(cursor.getInt(ageIndex)));
            cursor.moveToNext();
        }

        Log.i("Next....","Age>23");
        int nameIndex2= cursor2.getColumnIndex("name");
        int ageIndex2=cursor2.getColumnIndex("age");

        cursor2.moveToFirst();
        while(!cursor2.isAfterLast()){
            Log.i("Name...Age", cursor2.getString(nameIndex)+"..."+Integer.toString(cursor2.getInt(ageIndex)));
            cursor2.moveToNext();
        }

        Log.i("Next....","name has a");
        int nameIndex5= cursor5.getColumnIndex("name");
        int ageIndex5=cursor5.getColumnIndex("age");

        cursor5.moveToFirst();
        while(!cursor5.isAfterLast()){
            Log.i("Name...Age", cursor5.getString(nameIndex)+"..."+Integer.toString(cursor5.getInt(ageIndex)));
            cursor5.moveToNext();
        }

        //Delete
        //myDataBase.execSQL("DELETE FROM users WHERE name='Nikhil Khurana'");
    }
}