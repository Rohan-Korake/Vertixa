package com.example.vertixa;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class bmiCalculator extends AppCompatActivity {

    EditText weight,height;
    Button calculate;
    TextView bmiResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(getResources().getColor(android.R.color.black));
        setContentView(R.layout.activity_bmi_calculator);
        weight = findViewById(R.id.weight);
        height = findViewById(R.id.height);
        bmiResult = findViewById(R.id.bmiResult);

        findViewById(R.id.calculate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String weightStr = weight.getText().toString().trim();
                String heightStr = height.getText().toString().trim();

                if (weightStr.isEmpty() || heightStr.isEmpty()) {
                    bmiResult.setText("Please enter all fields");
                    return;
                }

                double weightVal = Double.parseDouble(weightStr);
                double heightVal = Double.parseDouble(heightStr);
                double hMeter = heightVal / 100;

                if (hMeter == 0) {
                    bmiResult.setText("Height cannot be zero");
                    return;
                }
                double bmi = weightVal / (hMeter * hMeter);

                String category;

                if (bmi < 18.5) {
                    category = "Underweight";
                } else if (bmi < 25) {
                    category = "Normal";
                } else if (bmi < 30) {
                    category = "Overweight";
                } else {
                    category = "Obese";
                }
                bmiResult.setText("BMI: " + String.format("%.2f", bmi) +"   "+ "Category: " + category);
            }
        });
    }
}