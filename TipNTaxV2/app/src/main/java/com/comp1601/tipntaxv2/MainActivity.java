package com.comp1601.tipntaxv2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    private EditText amountText;
    private Button calculateButton;
    private TextView totalText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        amountText = findViewById(R.id.txtBill);
        calculateButton = findViewById(R.id.btnCalculate);
        totalText =findViewById(R.id.txtTotal);


        calculateButton.setOnClickListener(v -> {

            String amountStr = amountText.getText().toString();

            /*
            double amount = Double.parseDouble(amountStr);
            TipNTaxCalculator calculator = new TipNTaxCalculator();
            double total = calculator.calculate(amount);
            totalText.setText(String.valueOf(total));
             */
            open();




        });



    }

    public void open(){
        Intent intent = new Intent(this, TipCalcActivity.class);
        startActivity(intent);
    }

}
