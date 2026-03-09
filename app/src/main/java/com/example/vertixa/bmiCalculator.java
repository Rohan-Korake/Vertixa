package com.example.vertixa;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class bmiCalculator extends AppCompatActivity {

    EditText weight, height;
    TextView bmiResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(getResources().getColor(android.R.color.black));
        setContentView(R.layout.activity_bmi_calculator);

        weight = findViewById(R.id.weight);
        height = findViewById(R.id.height);
        bmiResult = findViewById(R.id.bmiResult);

        // Handle home page button
        View homeButton = findViewById(R.id.homeButton);
        if (homeButton != null) {
            homeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(bmiCalculator.this, MainActivity.class);
                    startActivity(intent);
                }
            });
        }

        View calculateButton = findViewById(R.id.calculate);
        if (calculateButton != null) {
            calculateButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    calculateBMI();
                }
            });
        }
    }

    private void calculateBMI() {
        String weightStr = weight.getText().toString().trim();
        String heightStr = height.getText().toString().trim();

        if (weightStr.isEmpty() || heightStr.isEmpty()) {
            Toast.makeText(this, "Please enter all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double weightVal = Double.parseDouble(weightStr);
            double heightVal = Double.parseDouble(heightStr);

            if (heightVal <= 0) {
                Toast.makeText(this, "Height must be greater than zero", Toast.LENGTH_SHORT).show();
                return;
            }

            double hMeter = heightVal / 100;
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

            bmiResult.setText(String.format("BMI: %.2f\nCategory: %s", bmi, category));
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Please enter valid numbers", Toast.LENGTH_SHORT).show();
        }
    }
}