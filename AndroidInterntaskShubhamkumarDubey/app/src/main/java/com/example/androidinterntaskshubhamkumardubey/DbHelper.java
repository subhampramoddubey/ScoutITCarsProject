package com.example.androidinterntaskshubhamkumardubey;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.telecom.Call;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.ContentView;
import androidx.annotation.Nullable;

class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "CarDetails.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "my_details";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_MODEL = "my_details";

    MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {

        String query =
                "CREATE TABLE " + TABLE_NAME +
                        " ("+ COLUMN_ID + " INTEGER PRIMARY KEY, "
                        + COLUMN_MODEL + " TEXT); " ;
        db.execSQL(query);


    }


    @Override
    public void onUpgrade(SQLiteDatabase db,int i,int i1)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void addData(int makeId, String car_Model){


        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_ID,makeId);
        cv.put(COLUMN_MODEL,car_Model);
         long result = db.insert(TABLE_NAME,null,cv);
        if(result == -1)
        {
            Toast toast = Toast.makeText(context,"Failed",Toast.LENGTH_SHORT);
            toast.show();
        }
        else {
            Toast toast = Toast.makeText(context,"ADD Success",Toast.LENGTH_SHORT);

            toast.show();

        }
    }

    Cursor readALLData(){
        String query = " SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db !=null )
        {
            cursor = db.rawQuery(query,null);
        }
        return cursor;
    }


    void DeleteData( String row_id)
    {
//        String str = row_id.toString();

        SQLiteDatabase db = this.getWritableDatabase();
//        Toast.makeText(context,row_id,Toast.LENGTH_SHORT).show();


        long result = db.delete(TABLE_NAME,"id=?",new String[]{row_id});

        if(result == -1)
        {
            Toast.makeText(context,"Failed",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context,"Successfully Deleted",Toast.LENGTH_SHORT).show();
        }

    }

}
