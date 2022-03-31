package com.example.userprofile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FoodItemAdapter extends RecyclerView.Adapter<FoodItemAdapter.Foodviewholder> {
    ArrayList<Food> arrayList;
    Context context;

    public FoodItemAdapter(ArrayList<Food> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    public FoodItemAdapter() {

    }

    @NonNull
    @Override

    public FoodItemAdapter.Foodviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_item_view, parent, false);
        return new Foodviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodItemAdapter.Foodviewholder holder, int position) {

        holder.imageView.setImageResource(arrayList.get(position).getImg());
        holder.textview.setText(arrayList.get(position).getName());
        holder.textView.setText(arrayList.get(position).getDetail());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class Foodviewholder extends RecyclerView.ViewHolder {
        TextView textview;
        ImageView imageView;
        TextView textView;

        public Foodviewholder(@NonNull View itemView) {
            super(itemView);
            textview = itemView.findViewById(R.id.tv_halwa);
            textView = itemView.findViewById(R.id.discription);
            imageView = itemView.findViewById(R.id.iv_halwa);
        }
    }
}
