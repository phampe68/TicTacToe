package com.comp1601.tipntax;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    public static final String TIP_N_TAX_MAIN_AMOUNT = "tip_n_tax_main_activity_amount";
    private static final int TIP_N_TAX_TOTAL_RESULT = 0;
    private final String TAG = "MainActivity";
    private EditText amountText;
    private Button calculateButton;
    private TextView totalText;
    private Button visitButton;
    private EditText urlEditText;
    private Button sendButton;
    private EditText emailText;
    private EditText messageText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //inflate buttons
        amountText = findViewById(R.id.txtBill);
        calculateButton = findViewById(R.id.btnCalculate);
        totalText =findViewById(R.id.txtTotal);
        visitButton = findViewById(R.id.btnBrowser);
        urlEditText = findViewById(R.id.txtURL);
        sendButton = findViewById(R.id.btnSendEmail);
        emailText = findViewById(R.id.txtEmail);
        messageText = findViewById(R.id.txtMessageContent);


        visitButton.setOnClickListener(v->{
            Log.i(TAG, "Visit Button Clicked");
            String url = urlEditText.getText().toString();
            if(url.length() != 0){
                Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(webIntent);
            }
        });


        calculateButton.setOnClickListener(v -> {
            String amountStr = amountText.getText().toString();
            double amount = Double.parseDouble(amountStr);

            /*
            TipNTaxCalculator calculator = new TipNTaxCalculator();
            double total = calculator.calculate(amount);
            totalText.setText(String.valueOf(total));
             */

            Intent intent = new Intent(this, TipCalcActivity.class);
            intent.putExtra(TIP_N_TAX_MAIN_AMOUNT, amount);
            startActivityForResult(intent, TIP_N_TAX_TOTAL_RESULT);
        });

        sendButton.setOnClickListener(v->{
            String emailAddress = emailText.getText().toString();
            String subject = "TIP&TAX Calculator Report";
            String message = messageText.getText().toString();

            String emailURI = "mailto:" + emailAddress;
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse(emailURI));

            emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
            emailIntent.putExtra(Intent.EXTRA_TEXT, message);

            startActivity(Intent.createChooser(emailIntent, "Email Client ...."));
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        Log.i("TEST", String.valueOf(resultCode));

        if(resultCode != Activity.RESULT_OK)
            return;

        if(requestCode == TIP_N_TAX_TOTAL_RESULT){
            if(data == null)
                return;
            double computedTotal = data.getDoubleExtra(TipCalcActivity.TIP_N_TAX_COMPUTED_TOTAL, TipNTaxCalculator.InvalidResult);
            String computedTotalStr = "" + computedTotal;
            totalText.setText(computedTotalStr);
        }
    }

}
