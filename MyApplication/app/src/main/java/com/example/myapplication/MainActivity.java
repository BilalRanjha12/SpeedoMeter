package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    List_Custom myList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        /**
         * Issue here
         * */
        List_Custom[] my_lists=new List_Custom[]{
                new List_Custom("Raihan", android.R.drawable.ic_dialog_email),
                new List_Custom("Ali", android.R.drawable.ic_dialog_info),
                new List_Custom("Haider", android.R.drawable.ic_delete),
                new List_Custom("Asghar", android.R.drawable.ic_dialog_dialer),
        };
        MyListAdapter adapter = new MyListAdapter(my_lists);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);


    }
}