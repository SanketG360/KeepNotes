package com.example.todo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    EditText title,desc;
    Button btn_add;
    ArrayList<TodoModel>arrayListmodel = new ArrayList<>();
    DBHelper dbHelper = new DBHelper(this);
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        title = findViewById(R.id.title_todo);
        desc = findViewById(R.id.desc_todo);
        btn_add = findViewById(R.id.btn_add);


        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                display();
            }
        });
    }

    public void display() {
        String title_todo = title.getText().toString();
        String desc_todo = desc.getText().toString();
        boolean checkuserdata = dbHelper.addItem(title_todo,desc_todo);
        if(checkuserdata==true)
        {
            Toast.makeText(SecondActivity.this,"New Entry Inserted",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(SecondActivity.this,"New Entry Not Inserted",Toast.LENGTH_SHORT).show();

        }
        title.setText("");
        desc.setText("");

        title.setText("");
    }
}