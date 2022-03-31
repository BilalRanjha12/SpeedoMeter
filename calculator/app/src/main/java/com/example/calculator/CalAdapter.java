package com.example.calculator;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CalAdapter extends RecyclerView.Adapter<CalAdapter.Calviewholder> {
    private String[] data;

    public CalAdapter(String[] data) {
        this.data = data;
    }

    @NonNull
    @Override
    public Calviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.calculatordesigning, parent, false);
        return new Calviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Calviewholder holder, int position) {
String title=data[position];
holder.textView.setText(title);
    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public class Calviewholder extends RecyclerView.ViewHolder {
RelativeLayout relativeLayout;
TextView textView;
        public Calviewholder(@NonNull View itemView) {
            super(itemView);
            relativeLayout=itemView.findViewById(R.id.layout_1);
            textView=itemView.findViewById(R.id.text_view_1);
        }
    }
}
