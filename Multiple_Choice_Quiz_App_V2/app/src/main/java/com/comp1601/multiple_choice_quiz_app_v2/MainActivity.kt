package com.comp1601.multiple_choice_quiz_app_v2

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.annotation.RequiresApi

//NOTE: this extension allows us to access view objects without findViewByID
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var currentQuestionIndex = 0
        var totalScore = 0

        //create and fill array list of all questions
        val questions:ArrayList<Question> = ArrayList()
        questions.add(Question(resources.getStringArray(R.array.question1)))
        questions.add(Question(resources.getStringArray(R.array.question2)))

        //go through all questions and randomize answers
        for(i in 0 until questions.size){
            questions[i].shuffleAnswers()
        }

        //set question text and possible answers
        updateAnswersText(questions[currentQuestionIndex])


        //button on click listeners
        btnMultipleChoiceA.setOnClickListener{
            updateButtonBackground(btnMultipleChoiceA)
            //increment score if answer is correct
            if (checkAnswer("A", questions[currentQuestionIndex]))
                totalScore ++
            else
                totalScore--


        }
        btnMultipleChoiceB.setOnClickListener{
            updateButtonBackground(btnMultipleChoiceB)

            //increment score if answer is correct
            if (checkAnswer("B", questions[currentQuestionIndex]))
                totalScore ++
        }
        btnMultipleChoiceC.setOnClickListener{
            updateButtonBackground(btnMultipleChoiceC)
            //increment score if answer is correct
            if (checkAnswer("C", questions[currentQuestionIndex]))
                totalScore ++

        }
        btnMultipleChoiceD.setOnClickListener{
            updateButtonBackground(btnMultipleChoiceD)
            //increment score if answer is correct
            if (checkAnswer("D", questions[currentQuestionIndex]))
                totalScore ++

        }
        btnMultipleChoiceE.setOnClickListener{
            updateButtonBackground(btnMultipleChoiceE)
            //increment score if answer is correct
            if (checkAnswer("E", questions[currentQuestionIndex]))
                totalScore ++

        }

        btnNext.setOnClickListener{
            if(currentQuestionIndex < questions.size - 1){
                currentQuestionIndex++
                updateAnswersText(questions[currentQuestionIndex])
            }

        }
        btnPrevious.setOnClickListener{
            if(currentQuestionIndex > 0){
                currentQuestionIndex--
                updateAnswersText(questions[currentQuestionIndex])
            }


        }
        btnSubmit.setOnClickListener{
            //Display user score
            Toast.makeText(this, "You got a score of: $totalScore", Toast.LENGTH_LONG).show()
        }




    }

    private fun updateAnswersText(question:Question){
        val potentialAnswers = question.potentialAnswers
        val questionText = question.questionText

        var potentialAnswersText = ""
        val uppercaseASCIIAlphabet = 65 //represents where the first letter of the alphabet starts in the ASCII table (A)

        for(i in 0 until potentialAnswers.size){
            potentialAnswersText +="[" + (i+uppercaseASCIIAlphabet).toChar() + "] " +potentialAnswers[i] + "\n"
        }

        txtAnswers.text = potentialAnswersText
        txtQuestion.text = questionText
    }










    /**
     * change the background of a clicked button and reset backgrounds of all other buttons
     * @param currentMultipleChoiceButton: the button widget that has been clicked
     */
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun updateButtonBackground(currentMultipleChoiceButton: Button){
        //drawables for different button states
        val buttonBackgroundClicked = getDrawable(R.drawable.button_background_clicked)
        val buttonBackgroundDefault = getDrawable(R.drawable.button_background)

        //reset all button background
        btnMultipleChoiceA.background = buttonBackgroundDefault
        btnMultipleChoiceB.background = buttonBackgroundDefault
        btnMultipleChoiceC.background = buttonBackgroundDefault
        btnMultipleChoiceD.background = buttonBackgroundDefault
        btnMultipleChoiceE.background = buttonBackgroundDefault

        //change button background to clicked
        currentMultipleChoiceButton.background = buttonBackgroundClicked
    }

    //checks if user selected multiple choice is correct
    private fun checkAnswer(buttonLetter:String, question:Question):Boolean{

        //map button letters to their respective answers in the potentialAnswers Array List
        val buttonAnswerMap:HashMap<String, Int> = HashMap()
        buttonAnswerMap["A"] = 0
        buttonAnswerMap["B"] = 1
        buttonAnswerMap["C"] = 2
        buttonAnswerMap["D"] = 3
        buttonAnswerMap["E"] = 4

        //THIS MIGHT BE WRONG
        val userAnswerIndex: Int = buttonAnswerMap[buttonLetter]!!
        val userAnswer = question.potentialAnswers[userAnswerIndex]

        //check if answer is correct or not
        if (userAnswer == question.answer){
            return true
        }
        return false
    }

}
