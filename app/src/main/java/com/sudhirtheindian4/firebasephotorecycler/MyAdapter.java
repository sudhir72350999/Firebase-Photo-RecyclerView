package com.sudhirtheindian4.firebasephotorecycler;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ReactiveGuide;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MyAdapter extends  RecyclerView.Adapter<MyAdapter.myViewHolder> {
    ArrayList<model> datalist;
    Context context;

    public MyAdapter(ArrayList<model> datalist, Context context) {
        this.datalist = datalist;
        this.context = context;
    }

//    public MyAdapter(ArrayList<model> datalist) {
//        this.datalist = datalist;
//    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row,parent,false);
        View view = LayoutInflater.from(context).inflate(R.layout.single_row,parent,false);
        return new myViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        /// here we are using getter setter
        model item = datalist.get(position);

        holder.t1.setText(datalist.get(position).getName());
        holder.t2.setText(datalist.get(position).getEmail());


        /// for image
        try {
            /// picasso or glide dono me se koi bhi
            Glide.with(context).load(item.getProfileImage()).placeholder(R.drawable.profile).into(holder.profileImage);
        } catch (Exception e) {
            e.printStackTrace();
        }


        //        holder.t1.setOnClickListener(new View.OnClickListener() {   //// for item only
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.t1.getContext(),DetailActivity.class);
                intent.putExtra("uname",datalist.get(position).getName());
                intent.putExtra("uemail",datalist.get(position).getEmail());
//                intent.putExtra("image",item.getProfileImage());
                intent.putExtra("image",datalist.get(position).getProfileImage());



                /// dusre activity par jump krne ke liye set flag ka use kare
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                holder.t1.getContext().startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }

    class  myViewHolder extends RecyclerView.ViewHolder{

        TextView t1,t2;
        ImageView profileImage;


        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            t1 =itemView.findViewById(R.id.t1);
            t2 =itemView.findViewById(R.id.t2);
            profileImage= itemView.findViewById(R.id.profileImage);

        }
    }


}
