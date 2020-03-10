package com.comp1601.tipntax;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.os.Bundle;
import android.content.Intent;


public class TipCalcActivity extends AppCompatActivity {

    private EditText txtPrice;
    private EditText txtPercent;
    private EditText txtTotal;
    private Button btnCalc;
    public static final String TIP_N_TAX_COMPUTED_TOTAL = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip_calc);

        txtPrice = findViewById(R.id.txtPrice);
        txtPercent = findViewById(R.id.txtPercentage);
        txtTotal = findViewById(R.id.txtTotal);
        btnCalc = findViewById(R.id.btnCalculate);

        //get price data from main activity
        double price = getIntent().getDoubleExtra(MainActivity.TIP_N_TAX_MAIN_AMOUNT, 0.0);
        String priceStr = "" + price;
        txtPrice.setText(priceStr);

        btnCalc.setOnClickListener(v->{
            String priceStrLocal = txtPrice.getText().toString();
            Double priceLocal  = Double.parseDouble(priceStrLocal);

            String percentStr = txtPercent.getText().toString();
            double percent = Double.parseDouble(percentStr);

            TipNTaxCalculator calc = new TipNTaxCalculator();
            calc.setTipPercentage(percent);

            double total = calc.calculate(priceLocal);
            String totalStr = total + "";
            txtTotal.setText(totalStr);

            Intent data = new Intent();
            data.putExtra(TIP_N_TAX_COMPUTED_TOTAL, total);
            setResult(RESULT_OK, data);

        });
    }
}
