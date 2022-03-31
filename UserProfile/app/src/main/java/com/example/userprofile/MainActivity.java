package com.example.userprofile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Object FoodItemAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.rv_user);

        ArrayList<Food> arrayList = new ArrayList<Food>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        arrayList = new ArrayList(6);
        arrayList.add(new Food("Halwa", "Made by ahad", R.drawable.halwa));
        arrayList.add(new Food("chicken krahi", "made by kumail", R.drawable.pakistani_chicken_karahi));
        arrayList.add(new Food("kABAB", "Made by atif", R.drawable.seekh_kabaab));
        arrayList.add(new Food("Chiken biryani", "Made by shani", R.drawable.pakistani_chicken_biryani));
        arrayList.add(new Food("Mecroni", "Made by fahd", R.drawable.mecroni));
        arrayList.add(new Food("kheer", "Made by Ali", R.drawable.rice_kheer));
        FoodItemAdapter foodItemAdapter = new FoodItemAdapter(arrayList, this);
   recyclerView.setAdapter(foodItemAdapter);


    }
}