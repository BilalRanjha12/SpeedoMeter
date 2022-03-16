package com.example.calculator;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CalAdapter extends RecyclerView.Adapter<CalAdapter.Calviewholder> {
    @NonNull
    @Override
    public Calviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }
    @Override
    public void onBindViewHolder(@NonNull Calviewholder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class Calviewholder extends RecyclerView.ViewHolder {
        public Calviewholder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
