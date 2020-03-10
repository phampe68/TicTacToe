package com.comp1601.truefalsequiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button mYesButton;
    private Button mNoButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //inflate buttons
        mYesButton = findViewById(R.id.yes_button);
        mNoButton = findViewById(R.id.no_button);

        //initialize questions



        mYesButton.setOnClickListener(v -> {
            //handle yes button click
            String s = getString(R.string.yes_button_label);
            System.out.println("YOU PRESSED " + s);


            Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
        });

        mNoButton.setOnClickListener(v -> {
            //handle no Button click
            String s = getString(R.string.no_button_label);
            System.out.println("YOU PRESSED " + s);
            Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();

        });

    }
}
