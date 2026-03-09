package com.example.vertixa;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class unitConvertor extends AppCompatActivity {

    Spinner fromUnit, toUnit;
    EditText inputNumber;
    TextView resultNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(getResources().getColor(android.R.color.black));
        setContentView(R.layout.activity_unit_convertor);

        View mainView = findViewById(R.id.main);
        if (mainView != null) {
            ViewCompat.setOnApplyWindowInsetsListener(mainView, (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });
        }

        // Handle home page button
        View homeButton = findViewById(R.id.homeButton);
        if (homeButton != null) {
            homeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(unitConvertor.this, MainActivity.class);
                    startActivity(intent);
                }
            });
        }

        fromUnit = findViewById(R.id.fromUnit);
        toUnit = findViewById(R.id.toUnit);
        inputNumber = findViewById(R.id.inputNumber);
        resultNumber = findViewById(R.id.resultNumber);

        String[] units = {"SELECT", "MM", "CM", "M", "KM", "IN", "FT", "YD", "MI"};

        ArrayAdapter<String> fromUnitAdapter = new ArrayAdapter<>(
                this,
                R.layout.spinner_item,
                units
        );
        fromUnitAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        fromUnit.setAdapter(fromUnitAdapter);

        ArrayAdapter<String> toUnitAdapter = new ArrayAdapter<>(
                this,
                R.layout.spinner_item,
                units
        );
        toUnitAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        toUnit.setAdapter(toUnitAdapter);

        findViewById(R.id.convert).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fromValueUnit = fromUnit.getSelectedItem().toString();
                String toValueUnit = toUnit.getSelectedItem().toString();
                String inputValue = inputNumber.getText().toString().trim();

                if (fromValueUnit.equals("SELECT") || toValueUnit.equals("SELECT")) {
                    Toast.makeText(unitConvertor.this, "Please select units", Toast.LENGTH_SHORT).show();
                    return;
                } else if (inputValue.isEmpty()) {
                    Toast.makeText(unitConvertor.this, "Please Enter value", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    double inpValue = Double.parseDouble(inputValue);
                    // Factors relative to Meters (M)
                    double[] factor = {0, 0.001, 0.01, 1.0, 1000.0, 0.0254, 0.3048, 0.9144, 1609.344};
                    int fromIndex = fromUnit.getSelectedItemPosition();
                    int toIndex = toUnit.getSelectedItemPosition();

                    if (factor[fromIndex] == 0 || factor[toIndex] == 0) {
                        Toast.makeText(unitConvertor.this, "Invalid unit selection", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    double result = (inpValue * factor[fromIndex]) / factor[toIndex];
                    String formattedResult = String.format("%.4f", result);
                    // Trim trailing zeros and decimal point if not needed
                    if (formattedResult.contains(".")) {
                        formattedResult = formattedResult.replaceAll("0*$", "").replaceAll("\\.$", "");
                    }
                    resultNumber.setText(formattedResult);
                } catch (NumberFormatException e) {
                    Toast.makeText(unitConvertor.this, "Invalid number entered", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(unitConvertor.this, "An error occurred", Toast.LENGTH_SHORT).show();
                }
            }
        });

        findViewById(R.id.reset).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fromUnit.setSelection(0);
                toUnit.setSelection(0);
                inputNumber.setText("");
                resultNumber.setText("");
            }
        });
    }
}