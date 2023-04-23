package com.example.androidinterntaskshubhamkumardubey;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.Currency;

public class LoginDbHelper extends SQLiteOpenHelper {

    public static final String LOGINDBNAME = "Signup.db";
    public static final String TABLE_NAME = "allusers";
    private Context context;

    public LoginDbHelper(@Nullable Context context) {
        super(context, LOGINDBNAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDatabase) {
        MyDatabase.execSQL("create Table " + TABLE_NAME + " ( username TEXT primary key, password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDatabase, int i, int i1) {

        MyDatabase.execSQL("drop Table if exists "+ TABLE_NAME );
    }

    public  Boolean insertData(String username, String password)
    {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        long result = MyDatabase.insert(TABLE_NAME,null,contentValues);

        if(result == -1)
        {
            Toast toast = Toast.makeText(context,"Failed",Toast.LENGTH_SHORT);
            toast.show();
            return false;
        }
        else {
            Toast toast = Toast.makeText(context,"ADD Success",Toast.LENGTH_SHORT);

            toast.show();
            return true;

        }

    }

     Boolean checkUsername(String username)
    {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cursor = MyDatabase.rawQuery("Select * from alluser where username = ?",new String[]{username});

        if(cursor.getCount()>0)
        {
            return true;

        }else {
            return false;
        }

    }


      Boolean checkuserpassword(String username, String password){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cursor= MyDatabase.rawQuery("Select * from alluser where username = ? and password = ?",new String[]{username, password});
        if(cursor.getCount()>0)
        {
            return true;

        }else {
            return false;
        }
    }


}

