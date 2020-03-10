package com.comp1601.multiple_choice_quiz_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val myQuestion:TextView = findViewById(R.id.txtQuestion)
        val btnMultipleChoiceA:Button = findViewById(R.id.btnMultipleChoiceA)
        val btnMultipleChoiceB:Button = findViewById(R.id.btnMultipleChoiceB)
        val btnMultipleChoiceC:Button = findViewById(R.id.btnMultipleChoiceC)
        val btnMultipleChoiceD:Button = findViewById(R.id.btnMultipleChoiceD)
        val btnMultipleChoiceE:Button = findViewById(R.id.btnMultipleChoiceE)


        var currentQuestionIndex = 0

        //list of all questions in test
        val questions:ArrayList<Question> = ArrayList()

        questions.add(Question(getString(R.string.question1)))

        //show first question
        myQuestion.text = questions[currentQuestionIndex].getQuestion()



        btnMultipleChoiceA.setOnClickListener{

        }









    }
}
