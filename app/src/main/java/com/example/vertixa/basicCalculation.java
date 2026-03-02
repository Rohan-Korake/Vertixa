package com.example.vertixa;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class basicCalculation extends AppCompatActivity {

    TextView OutputScreen;
    String screenvalues = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_calculation);

        OutputScreen = findViewById(R.id.OutputScreen);

        // AC Button
        findViewById(R.id.ac).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                screenvalues = "";
                OutputScreen.setText("");
            }
        });

        // Equal Button
        findViewById(R.id.equal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (screenvalues.isEmpty()) return;

                double totalvalue = 0;
                double currentNumber = 0;
                double decimalFactor = 1;
                boolean isDecimal = false;
                char operator = '+';

                for (int i = 0; i < screenvalues.length(); i++) {

                    char ch = screenvalues.charAt(i);

                    // Build number including decimal
                    if (Character.isDigit(ch)) {
                        if (!isDecimal) {
                            currentNumber = currentNumber * 10 + (ch - '0');
                        } else {
                            decimalFactor /= 10;
                            currentNumber = currentNumber + (ch - '0') * decimalFactor;
                        }
                    } else if (ch == '.') {
                        isDecimal = true;
                    }

                    // If operator OR last character
                    if ((!Character.isDigit(ch) && ch != '.') || i == screenvalues.length() - 1) {

                        switch (operator) {
                            case '+': totalvalue += currentNumber; break;
                            case '-': totalvalue -= currentNumber; break;
                            case '*': totalvalue *= currentNumber; break;
                            case '/':
                                if (currentNumber == 0) {
                                    OutputScreen.setText("Error");
                                    return;
                                }
                                totalvalue /= currentNumber; break;
                            case '%': totalvalue %= currentNumber; break;
                        }
                        operator = ch;
                        currentNumber = 0;
                        decimalFactor = 1;
                        isDecimal = false;
                    }
                }

                if (totalvalue == (int) totalvalue) {
                    OutputScreen.setText(String.valueOf((int) totalvalue));
                } else {
                    OutputScreen.setText(String.valueOf(totalvalue));
                }
                screenvalues = String.valueOf(totalvalue);
            }
        });
    }

    // Number Buttons
    public void handleInputNumber(View view) {
        Button button = (Button) view;
        String value = button.getText().toString();

        if (value.equals(".") && screenvalues.endsWith(".")) return;

        screenvalues += value;
        OutputScreen.setText(screenvalues);
    }

    // Operator Buttons
    public void handleInputOperator(View view) {
        Button button = (Button) view;
        String value = button.getText().toString();

        if (screenvalues.isEmpty()) return;
        char lastChar = screenvalues.charAt(screenvalues.length() - 1);
        if (lastChar == '+' || lastChar == '-' || lastChar == '*' || lastChar == '/') return;

        screenvalues += value;
        OutputScreen.setText(screenvalues);
    }
}