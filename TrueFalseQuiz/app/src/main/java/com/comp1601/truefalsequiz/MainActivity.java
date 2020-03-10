package com.comp1601.truefalsequiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

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

        ArrayList<Question> questions = new ArrayList<>();
        questions.add(new Question(getString(R.string.question1)));
        questions.add(new Question(getString(R.string.question2)));
        questions.add(new Question(getString(R.string.question3)));
        questions.add(new Question(getString(R.string.question4)));
        questions.add(new Question(getString(R.string.question5)));

        for (Question q: questions){
            System.out.println(q.getQuestion());
            System.out.println(q.getAnswer());
        }


        mYesButton.setOnClickListener(v -> {
            //handle yes button click
            String s = getString(R.string.yes_button_label);
            System.out.println("YOU PRESSED AA" + s);


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
