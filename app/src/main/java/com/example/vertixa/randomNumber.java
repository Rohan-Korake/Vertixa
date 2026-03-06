package com.example.vertixa;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class randomNumber extends AppCompatActivity {

    EditText start, end;
    TextView output;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_random_number);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        start = findViewById(R.id.start);
        end = findViewById(R.id.end);
        output = findViewById(R.id.output);

        //        Handle Generate Button event
        findViewById(R.id.generate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String minStr = start.getText().toString().trim();
                String maxStr = end.getText().toString().trim();

                // Check both empty
                if (minStr.isEmpty() && maxStr.isEmpty()) {
                    Toast.makeText(randomNumber.this, "Enter Min and Max values", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Check min empty
                if (minStr.isEmpty()) {
                    Toast.makeText(randomNumber.this, "Enter Min value", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Check max empty
                if (maxStr.isEmpty()) {
                    Toast.makeText(randomNumber.this, "Enter Max value", Toast.LENGTH_SHORT).show();
                    return;
                }

                int min = Integer.parseInt(minStr);
                int max = Integer.parseInt(maxStr);

                // Check equal numbers
                if (min == max) {
                    Toast.makeText(randomNumber.this, "Min and Max cannot be same", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Check invalid range
                if (min > max) {
                    Toast.makeText(randomNumber.this, "Min must be less than Max", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Generate random number
                Random rand = new Random();
                int randomNum = rand.nextInt((max - min) + 1) + min;

                output.setText(String.valueOf(randomNum));
            }
        });

//        Handle reset Button event
        findViewById(R.id.reset).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                output.setText("00");
                start.setText("Min");
                end.setText("Max");
            }
        });
    }
}