package com.example.todo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyTodoAdapter extends RecyclerView.Adapter<MyTodoAdapter.MyViewHolder> {
    private Context context;
    Activity activity;
    DBHelper db = new DBHelper(context);
    ArrayList<TodoModel>arrayListModel;

    public MyTodoAdapter(Activity activity, Context context,ArrayList<TodoModel> arrayListmodel) {
        this.activity = activity;
        this.context = context;
        this.arrayListModel = arrayListmodel;
    }

    @NonNull
    @Override
    public MyTodoAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.userentry,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyTodoAdapter.MyViewHolder holder, int position) {
        TodoModel todoModel = (TodoModel) arrayListModel.get(position);
        holder.title_card.setText(arrayListModel.get(position).title_mode);
        holder.desc_card.setText(arrayListModel.get(position).desc_model);
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent intent = new Intent(context,Update_activity.class);
                String title = arrayListModel.get(position).title_mode;
                String desc =  arrayListModel.get(position).desc_model;
                int id  = arrayListModel.get(position).id;
                intent.putExtra("title_chnge",title);
                intent.putExtra("desc_chnge",desc);
                intent.putExtra("id_updet",String.valueOf(id));
                activity.startActivityForResult(intent,1);
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayListModel.size();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title_card,desc_card;
        LinearLayout linearLayoutcard;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title_card = itemView.findViewById(R.id.texttitle);
            desc_card = itemView.findViewById(R.id.textedesc);
            linearLayoutcard = itemView.findViewById(R.id.card);
        }
    }
}
