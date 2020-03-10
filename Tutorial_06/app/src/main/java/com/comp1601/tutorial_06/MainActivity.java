package com.comp1601.tutorial_06;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.comp1601.tutorial_06.Question;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Button mYesButton;
    private Button mNoButton;
    private TextView questionText;
    private int currentQuestionIndex = 0;


    private final String TAG = this.getClass().getSimpleName() + " @" + System.identityHashCode(this);



    private String getDeviceInfo(){
        String s = "Device Info:";
        try{
            s += "\n OS Version: " + System.getProperty("os.version") + "(" + Build.VERSION.INCREMENTAL + ")";
            s += "\n OS API Level: " + Build.VERSION.SDK_INT;
            s += "\n Device: " + Build.DEVICE;
            s += "\n Model (and Product):" + Build.MODEL + "(" + Build.PRODUCT + ")";
            s += "\n RELEASE: " + Build.VERSION.RELEASE;
            s += "\n BRAND: "  + Build.BRAND;
            s += "\n DISPLAY: "  + Build.DISPLAY;
            s += "\n HARDWARE: "  + Build.HARDWARE;
            s += "\n Built ID: "  + Build.ID;
            s += "\n MANUFACTURER: "  + Build.MANUFACTURER;
            s += "\n USER: "  + Build.USER;
            s += "\n HOST: "  + Build.HOST;




        } catch (Exception e){
            Log.e(TAG, "Error getting Device INFO");
        }

        return s;

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //inflate buttons
        mYesButton = findViewById(R.id.yes_button);
        mNoButton = findViewById(R.id.no_button);
        questionText = findViewById(R.id.txtQuestion);


        ArrayList<Question> questions = new ArrayList<>();
        questions.add(new Question(getString(R.string.question1)));
        questions.add(new Question(getString(R.string.question2)));
        questions.add(new Question(getString(R.string.question3)));
        questions.add(new Question(getString(R.string.question4)));
        questions.add(new Question(getString(R.string.question5)));


        //set first question text
        questionText.setText(questions.get(currentQuestionIndex).getQuestion());

        mYesButton.setOnClickListener(v -> {
            //check if question is right or wrong
            if(questions.get(currentQuestionIndex).getAnswer().equals("Yes"))
                Toast.makeText(MainActivity.this, R.string.correct_answer_toast, Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(MainActivity.this, R.string.wrong_answer_toast, Toast.LENGTH_SHORT).show();


            //move to next question
            currentQuestionIndex ++;

            //reset index if we reached final question
            if(currentQuestionIndex >= questions.size())
                currentQuestionIndex = 0;

            //set the text for the next question
            questionText.setText(questions.get(currentQuestionIndex).getQuestion());


            //DEBUG: log yes click
            Log.i(TAG, "Yes Button Clicked!");

            Log.i(TAG, getDeviceInfo());

        });

        mNoButton.setOnClickListener(v -> {
            //check if question is right or wrong
            if(questions.get(currentQuestionIndex).getAnswer().equals("Yes"))
                Toast.makeText(MainActivity.this, R.string.correct_answer_toast, Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(MainActivity.this, R.string.wrong_answer_toast, Toast.LENGTH_SHORT).show();


            //move to next question
            currentQuestionIndex ++;

            //reset index if we reached final question
            if(currentQuestionIndex >= questions.size())
                currentQuestionIndex = 0;

            //set the text for the next question
            questionText.setText(questions.get(currentQuestionIndex).getQuestion());


            //DEBUG: no button click
            Log.i(TAG, "No Button Clicked!");

        });

    }
}
