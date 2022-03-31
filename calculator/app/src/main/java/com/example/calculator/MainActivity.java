package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView=findViewById(R.id.recyclerVie);
        recyclerView.setLayoutManager(new GridLayoutManager(this,3));
        String[] symbols={"1","2","3","4","5","6","7","8","9","0"};
recyclerView.setAdapter(new CalAdapter(symbols));
    }
}