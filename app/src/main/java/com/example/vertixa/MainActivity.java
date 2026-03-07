package com.example.vertixa;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(Color.parseColor("#0B0F19")); // your dark color
        }
        getWindow().setStatusBarColor(getResources().getColor(android.R.color.black));

        findViewById(R.id.basicCal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, basicCalculation.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.bmiCal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,bmiCalculator.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.ageCal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,ageCalculator.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.countDown).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,countDownTimer.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.randomNum).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, randomNumber.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.unitCon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, unitConvertor.class);
                startActivity(intent);
            }
        });
    }
}