package com.example.todo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class Update_activity extends AppCompatActivity {

    EditText title,descp;
    Button btn_upd;
    String id;
    String title1,description;
    DBHelper dbHelper= new DBHelper(this);
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        title = findViewById(R.id.title_todo);
        descp = findViewById(R.id.desc_todo);
        btn_upd = findViewById(R.id.btn_update);
        getAndsetIntentData();
        btn_upd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title1 = title.getText().toString();
                description = descp.getText().toString();
               boolean r = dbHelper.update_data(Integer.parseInt(id),title1,description);
                if(r==true)
                {
                    Toast.makeText(getApplicationContext(),"Data Updated Successfully",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Data Not  Updated Successfully",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    void  getAndsetIntentData()
    {
        if(getIntent().hasExtra("id_updet") && getIntent().hasExtra("title_chnge")  && getIntent().hasExtra("desc_chnge"))
        {
             id = getIntent().getStringExtra("id_updet");
             title1 = getIntent().getStringExtra("title_chnge");
            description = getIntent().getStringExtra("desc_chnge");

            title.setText(title1);
            descp.setText( description);
        }
        else
        {
            Toast.makeText(getApplicationContext(),"No Data.",Toast.LENGTH_SHORT).show();

        }
    }
}