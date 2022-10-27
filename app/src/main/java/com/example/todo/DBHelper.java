package com.example.todo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper  extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "ToDoAPP.db";
    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table todolist (id INTEGER PRIMARY KEY AUTOINCREMENT , title TEXT not null,description TEXT not null)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ToDoContract.ToDoListEntry.Table_Name);
        onCreate(db);
    }
    public boolean addItem(String title_todo,String desc_todo)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(ToDoContract.ToDoListEntry.Title, title_todo);
        cv.put(ToDoContract.ToDoListEntry.Description, desc_todo);
        long r =DB.insert(ToDoContract.ToDoListEntry.Table_Name,null,cv);
        if(r==-1)return  false;
        else
            return true;
    }
    public ArrayList<TodoModel> getData()
    {
        SQLiteDatabase DB = this.getReadableDatabase();
        Cursor cursor = DB.rawQuery(" select * from todolist ",null);
        ArrayList<TodoModel> storedata = new ArrayList<>();
        while(cursor.moveToNext())
        {

            int id = cursor.getInt(0);
            String title = cursor.getString(1);
            String desc = cursor.getString(2);
            storedata.add(new TodoModel(id,title,desc));
        }
        return storedata;
    }
    void removeItem(int id) {

        SQLiteDatabase DB = this.getWritableDatabase();
        DB.delete(ToDoContract.ToDoListEntry.Table_Name, ToDoContract.ToDoListEntry.ID+" = "+id,null);
        DB.close();
    }
    public boolean update_data(int id, String title_updte, String desc_updte)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(ToDoContract.ToDoListEntry.Title,title_updte);
        cv.put(ToDoContract.ToDoListEntry.Description, desc_updte);
        long result =  DB.update(ToDoContract.ToDoListEntry.Table_Name,cv,ToDoContract.ToDoListEntry.ID+" =? ", new String[]{String.valueOf(id)});
        if(result==-1)
        {
            return false;

        }
        else {
            return true;
        }

    }

}
