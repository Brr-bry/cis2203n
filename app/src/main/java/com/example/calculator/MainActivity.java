package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    double firstNumber = 0;
    double secondNumber = 0;
    String operator = "";
    String expression = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView label = findViewById(R.id.input);
        TextView recent = findViewById(R.id.historyLabel);

        Button zeroBtn = findViewById(R.id.zeroBtn);
        Button oneBtn = findViewById(R.id.oneBtn);
        Button twoBtn = findViewById(R.id.twoBtn);
        Button threeBtn = findViewById(R.id.threeBtn);
        Button fourBtn = findViewById(R.id.fourBtn);
        Button fiveBtn = findViewById(R.id.fiveBtn);
        Button sixBtn = findViewById(R.id.sixBtn);
        Button sevenBtn = findViewById(R.id.sevenBtn);
        Button eightBtn = findViewById(R.id.eightBtn);
        Button nineBtn = findViewById(R.id.nineBtn);

        Button addBtn = findViewById(R.id.addBtn);
        Button subtractBtn = findViewById(R.id.subtractBtn);
        Button multiplyBtn = findViewById(R.id.multiplyBtn);
        Button divideBtn = findViewById(R.id.divideBtn);

        Button equalBtn = findViewById(R.id.equalBtn);
        Button decimalBtn = findViewById(R.id.decimalBtn);
        Button signBtn = findViewById(R.id.signBtn);
        Button idBtn = findViewById(R.id.idBtn);

        Button delBtn = findViewById(R.id.delBtn);
        Button clearBtn = findViewById(R.id.clearBtn);

        label.setText("0");

        View.OnClickListener numberClick = v -> {
            Button btn = (Button) v;
            String value = btn.getText().toString();

            if(expression.equals("0")) expression = "";
            if(expression.endsWith("-") && expression.contains(" ")) expression += value;
            else expression += value;
            label.setText(expression);
        };

        zeroBtn.setOnClickListener(numberClick);
        oneBtn.setOnClickListener(numberClick);
        twoBtn.setOnClickListener(numberClick);
        threeBtn.setOnClickListener(numberClick);
        fourBtn.setOnClickListener(numberClick);
        fiveBtn.setOnClickListener(numberClick);
        sixBtn.setOnClickListener(numberClick);
        sevenBtn.setOnClickListener(numberClick);
        eightBtn.setOnClickListener(numberClick);
        nineBtn.setOnClickListener(numberClick);

        View.OnClickListener operatorClick = v -> {
            Button btn = (Button) v;
            String newOperator = btn.getText().toString();

            if(expression.contains(" ")) {
                String[] parts = expression.split(" ");

                if(parts.length < 3 || parts[2].isEmpty() || parts[2].equals("-")) {
                    expression = parts[0] + " " + newOperator + " ";
                    operator = newOperator;
                    label.setText(expression);
                    return;
                }

                return;
            }

            operator = newOperator;
            firstNumber = Double.parseDouble(expression);
            expression += " " + operator + " ";
            label.setText(expression);
        };

        addBtn.setOnClickListener(operatorClick);
        subtractBtn.setOnClickListener(operatorClick);
        multiplyBtn.setOnClickListener(operatorClick);
        divideBtn.setOnClickListener(operatorClick);

        decimalBtn.setOnClickListener(v -> {
            if(expression.contains(" ")) {
                String[] parts = expression.split(" ");
                if(parts.length < 3 || parts[2].isEmpty()) expression += "0.";
                else if(!parts[2].contains(".")) expression += ".";
            } else {
                if(!expression.contains(".")) expression += ".";
            }
            label.setText(expression);
        });

        signBtn.setOnClickListener(v -> {
            if(expression.contains(" ")) {
                String[] parts = expression.split(" ");
                if(parts.length < 3 || parts[2].isEmpty()) {
                    if(!expression.endsWith("-")) expression += "-";
                } else {
                    double num = Double.parseDouble(parts[2]);
                    num *= -1;
                    parts[2] = formatResult(num);
                    expression = parts[0] + " " + parts[1] + " " + parts[2];
                }
            } else {
                double num = Double.parseDouble(expression);
                num *= -1;
                expression = formatResult(num);
            }
            label.setText(expression);
        });



        equalBtn.setOnClickListener(v -> {
            if(!expression.contains(" ") || expression.endsWith(" ") || expression.endsWith("-")) return;

            String[] parts = expression.split(" ");
            firstNumber = Double.parseDouble(parts[0]);
            operator = parts[1];
            secondNumber = Double.parseDouble(parts[2]);

            double result = 0;

            switch(operator) {
                case "+": result = firstNumber + secondNumber; break;
                case "-": result = firstNumber - secondNumber; break;
                case "x": result = firstNumber * secondNumber; break;
                case "÷":
                    if(secondNumber == 0) {
                        label.setText("Cannot divide by 0");
                        recent.setText(formatResult(firstNumber) + " " + operator + " " + formatResult(secondNumber));
                        return;
                    }
                    result = firstNumber / secondNumber;
                    break;
            }

            recent.setText(formatResult(firstNumber) + " " + operator + " " + formatResult(secondNumber));
            String formatted = formatResult(result);
            label.setText(formatted);

            expression = formatted;
            operator = "";
        });

        clearBtn.setOnClickListener(v -> {
            label.setText("0");
            recent.setText("");
            expression = "0";
            firstNumber = 0;
            secondNumber = 0;
            operator = "";
        });

        delBtn.setOnClickListener(v -> {
            if(expression.length() <= 1) expression = "0";
            else {
                expression = expression.substring(0, expression.length() - 1);
                if(expression.endsWith(" ")) expression = expression.substring(0, expression.length() - 1);
            }
            label.setText(expression);
        });
    }

    private String formatResult(double result) {
        if(result == (long) result) return String.valueOf((long) result);
        else return String.valueOf(result);
    }
}