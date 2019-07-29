package com.example.calenderautocomplete;

import android.app.DatePickerDialog;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    TextView date;
    AutoCompleteTextView a;
    SQLiteDatabase db;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    String c;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        date = findViewById(R.id.date);
        db=openOrCreateDatabase("myDB",MODE_PRIVATE,null);
        db.execSQL("CREATE TABLE IF NOT EXISTS tble(name_tb varchar);");
        //Show the suggestion value

        String[] value={"mani","ajay"};


        a=findViewById(R.id.editText);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.select_dialog_item,value);
        a.setThreshold(2);
        a.setAdapter(adapter);
        a.setTextColor(Color.RED);
    }

    public void timeClick(View view)
    {
        if (view.getId() == R.id.date)
        {
            Calendar cal = Calendar.getInstance();
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH);
            int day = cal.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dialog = new DatePickerDialog(this, android.R.style.Theme_DeviceDefault_Dialog, mDateSetListener, year, month, day);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();
        }
        mDateSetListener = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day)
            {
                month = month + 1;
                Log.d("MainActivity", "onDateSet:mm/dd/yyyy:" + month + "/" + day + "/" + year);
                String dateS = month + "/" + day + "/" + year;
                date.setText(dateS);
            }
        };
    }
}
