package com.example.todo;

import android.provider.BaseColumns;

public class ToDoContract {

    public static final class ToDoListEntry implements BaseColumns
    {
        public  static  final  String Table_Name = "todolist";
        public  static  final  String Title = "title";
        public  static  final  String Description = "description";
        public  static  final  String ID = "id";
    }
}
