package com.leeddev.myapplication;

import android.content.Intent;
import android.os.Build;;
import android.os.Bundle;

import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CalculatedAgeActivity extends AppCompatActivity {

    public static final String TAG = CalculatedAgeActivity.class.getSimpleName();
    Calendar mNow = Calendar.getInstance();
    private int[] mDates;
    private static final int DAY_INDEX = 0;
    private static final int MONTH_INDEX = 1;
    private static final int YEAR_INDEX = 2;

    private List<ConvertedVal> mConversionList;
    private RecyclerView recyclerView;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview_activity);

        recyclerView = findViewById(R.id.recyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);


        Intent intent = getIntent();
        mDates = intent.getIntArrayExtra("dates");

        initialiseData();
        initialiseAdapter();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public long getDiff(DifferenceIn duration) {
        int currentMonth = mNow.get(Calendar.MONTH)+1; //+1 since months are 0 indexed
//        Log.d(TAG, "current month: " + currentMonth);

        int currentDayOfMonth = mNow.get(Calendar.DAY_OF_MONTH);
//        Log.d(TAG, "current day: " + currentDayOfYear);

        int currentYear = mNow.get(Calendar.YEAR);
//        Log.d(TAG, "current year: " + currentYear);

        LocalDate startDate = LocalDate.of(mDates[YEAR_INDEX], mDates[MONTH_INDEX], mDates[DAY_INDEX]);
        LocalDate endDate = LocalDate.of(currentYear, currentMonth, currentDayOfMonth);

        long diff = 0; //default value

        switch (duration) {
            case SECONDS:
                LocalDateTime startDateSec = LocalDateTime.of(mDates[YEAR_INDEX], mDates[MONTH_INDEX],
                        mDates[DAY_INDEX], 0, 0, 0);

                LocalDateTime endDateSec = LocalDateTime.of(currentYear, currentMonth,
                        currentDayOfMonth,
                        mNow.get(Calendar.HOUR_OF_DAY),
                        mNow.get(Calendar.MINUTE),
                        mNow.get(Calendar.SECOND));

                LocalDateTime temp = LocalDateTime.from(startDateSec);

                diff = temp.until(endDateSec, ChronoUnit.SECONDS);
                Log.d(TAG, "seconds diff: " + diff);
                break;

            case DAYS:
                diff = ChronoUnit.DAYS.between(startDate, endDate);
                Log.d(TAG, "days diff: " + diff);
                break;

            case WEEKS:
                diff = ChronoUnit.WEEKS.between(startDate, endDate);
                Log.d(TAG, "weeks diff: " + diff);
                break;

            case MONTHS:
                diff = ChronoUnit.MONTHS.between(startDate, endDate);
                Log.d(TAG, "months diff: " + diff);
                break;
        }

        return diff;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void initialiseData() {
        mConversionList = new ArrayList<>();
        mConversionList.add(new ConvertedVal(" " + getDiff(DifferenceIn.MONTHS), "months"));
        mConversionList.add(new ConvertedVal(" " + getDiff(DifferenceIn.WEEKS), "weeks"));
        mConversionList.add(new ConvertedVal(" " + getDiff(DifferenceIn.DAYS), "days"));
        mConversionList.add(new ConvertedVal(" " + getDiff(DifferenceIn.SECONDS), "seconds"));
    }

    private void initialiseAdapter() {
//        Log.d(TAG, "in initialiseAdapter()");
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this,mConversionList);
        recyclerView.setAdapter(adapter);
    }
}
