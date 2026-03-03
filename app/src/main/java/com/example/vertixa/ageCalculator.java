package com.example.vertixa;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;

public class ageCalculator extends AppCompatActivity {

    TextView birthDate,ageAtDate,totalAge;
     Button calculateAge;
    int birthYear,birthMonth,birthDay,ageOfYear,ageOfMonth,ageOfDay;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_age_calculator);
        getWindow().setStatusBarColor(getResources().getColor(android.R.color.black));

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (view, windowInsets) -> {
            Insets insets = windowInsets.getInsets(WindowInsetsCompat.Type.statusBars());
            view.setPadding(view.getPaddingLeft(), insets.top, view.getPaddingRight(), view.getPaddingBottom());
            return WindowInsetsCompat.CONSUMED;
        });

        birthDate = findViewById(R.id.birthDate);
        ageAtDate= findViewById(R.id.ageAtDate);
        totalAge=findViewById(R.id.totalAge);

        birthDate.setOnClickListener(v-> acceptDate("birthDate") );
        ageAtDate.setOnClickListener(v -> acceptDate("ageAtDate") );

        findViewById(R.id.calculateAge).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(birthDate.getText().toString().isEmpty()){
                    birthDate.setError("Select Birth Date");
                    return;
                }

                if(ageAtDate.getText().toString().isEmpty()){
                    ageAtDate.setError("Select End Date");
                    return;
                }

                int ageYear = ageOfYear - birthYear;
                int ageMonth = ageOfMonth - birthMonth;
                int ageDay = ageOfDay - birthDay;

                if (ageDay < 0) {
                    ageMonth--;
                    ageDay += 30;
                }

                if (ageMonth < 0) {
                    ageYear--;
                    ageMonth += 12;
                }
                totalAge.setText(ageYear+" Years, " +ageMonth+" Months, " +ageDay+" Days");
            }
        });
  }
    public void acceptDate(String id){
        Calendar calendar=Calendar.getInstance();
        int year=calendar.get(calendar.YEAR);
        int month=calendar.get(calendar.MONTH);
        int day=calendar.get(calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog=new DatePickerDialog(this,(DatePicker view,int y,int m,int d)->{
            if ("birthDate".equals(id)) {
                birthYear=y;
                birthMonth=m;
                birthDay=d;
                birthDate.setText(d + "/" + (m + 1) + "/" + y);
            } else if ("ageAtDate".equals(id)) {
                ageOfYear=y;
                ageOfMonth=m;
                ageOfDay=d;
                ageAtDate.setText(d + "/" + (m + 1) + "/" + y);
            }
        },year,month,day);

        datePickerDialog.show();
    }

}
