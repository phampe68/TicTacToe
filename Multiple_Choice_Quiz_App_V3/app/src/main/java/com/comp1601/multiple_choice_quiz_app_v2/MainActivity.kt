package com.comp1601.multiple_choice_quiz_app_v2

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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
        questions.add(Question(resources.getStringArray(R.array.question3)))
        questions.add(Question(resources.getStringArray(R.array.question4)))
        questions.add(Question(resources.getStringArray(R.array.question5)))
        questions.add(Question(resources.getStringArray(R.array.question6)))
        questions.add(Question(resources.getStringArray(R.array.question7)))
        questions.add(Question(resources.getStringArray(R.array.question8)))
        questions.add(Question(resources.getStringArray(R.array.question9)))
        questions.add(Question(resources.getStringArray(R.array.question10)))

        //go through all questions and randomize answers
        for(i in 0 until questions.size){
            questions[i].shuffleAnswers()
        }

        //set question text and possible answers
        updateQuestionAnswersText(questions[currentQuestionIndex])

        //button on click listeners
        btnMultipleChoiceA.setOnClickListener{
            val currentQuestion = questions[currentQuestionIndex]

            //change score (if the user's hasn't already chosen this answer and the answer is correct)
            if(currentQuestion.userAnswer != "A" && checkAnswer("A", currentQuestion)){
                totalScore++
            }
            else if(currentQuestion.userAnswer != "UNANSWERED" && currentQuestion.userAnswer != "A" && checkAnswer(currentQuestion.userAnswer, currentQuestion))
                totalScore--

            //set the user's chosen answer
            currentQuestion.userAnswer = "A"
            //change BG to show what user chose
            updateButtonBackground(btnMultipleChoiceA)

        }
        btnMultipleChoiceB.setOnClickListener{
            val currentQuestion = questions[currentQuestionIndex]

            //change score (if the user's hasn't already chosen this answer and the answer is correct)
            if(currentQuestion.userAnswer != "B" && checkAnswer("B", currentQuestion)){
                totalScore++
            }
            else if(currentQuestion.userAnswer != "UNANSWERED" && currentQuestion.userAnswer != "B" && checkAnswer(currentQuestion.userAnswer, currentQuestion))
                totalScore--

            //set the user's chosen answer
            currentQuestion.userAnswer = "B"
            //change BG to show what user chose
            updateButtonBackground(btnMultipleChoiceB)

        }
        btnMultipleChoiceC.setOnClickListener{
            val currentQuestion = questions[currentQuestionIndex]

            //change score (if the user's hasn't already chosen this answer and the answer is correct)
            if(currentQuestion.userAnswer != "C" && checkAnswer("C", currentQuestion)){
                totalScore++
            }
            else if(currentQuestion.userAnswer != "UNANSWERED" && currentQuestion.userAnswer != "C"  && checkAnswer(currentQuestion.userAnswer, currentQuestion))
                totalScore--

            //set the user's chosen answer
            currentQuestion.userAnswer = "C"
            //change BG to show what user chose
            updateButtonBackground(btnMultipleChoiceC)

        }
        btnMultipleChoiceD.setOnClickListener{
            val currentQuestion = questions[currentQuestionIndex]

            //change score (if the user's hasn't already chosen this answer and the answer is correct)
            if(currentQuestion.userAnswer != "D" && checkAnswer("D", currentQuestion)){
                totalScore++
            }
            else if(currentQuestion.userAnswer != "UNANSWERED" && currentQuestion.userAnswer != "D"  && checkAnswer(currentQuestion.userAnswer, currentQuestion))
                totalScore--

            //set the user's chosen answer
            currentQuestion.userAnswer = "D"
            //change BG to show what user chose
            updateButtonBackground(btnMultipleChoiceD)

        }
        btnMultipleChoiceE.setOnClickListener{
            val currentQuestion = questions[currentQuestionIndex]

            //change score (if the user's hasn't already chosen this answer and the answer is correct)
            if(currentQuestion.userAnswer != "E" && checkAnswer("E", currentQuestion)){
                totalScore++
            }
            else if(currentQuestion.userAnswer != "UNANSWERED" && currentQuestion.userAnswer != "E"  && checkAnswer(currentQuestion.userAnswer, currentQuestion))
                totalScore--


            //set the user's chosen answer
            currentQuestion.userAnswer = "E"
            //change BG to show what user chose
            updateButtonBackground(btnMultipleChoiceE)

        }

        btnNext.setOnClickListener{
            //update current question index as long we're not on the last question
            val lastQuestionIndex = questions.size - 1

            if(currentQuestionIndex < lastQuestionIndex){

                //reset all button backgrounds
                val buttonBackgroundDefault = getDrawable(R.drawable.button_background)
                btnMultipleChoiceA.background = buttonBackgroundDefault
                btnMultipleChoiceB.background = buttonBackgroundDefault
                btnMultipleChoiceC.background = buttonBackgroundDefault
                btnMultipleChoiceD.background = buttonBackgroundDefault
                btnMultipleChoiceE.background = buttonBackgroundDefault

                //update which question we're on
                currentQuestionIndex++


                //change UI to reflect new question and possible answers
                updateQuestionAnswersText(questions[currentQuestionIndex])
            }
        }

        btnPrevious.setOnClickListener{
            //change current question index as long as we're not on the first question
            val firstQuestionIndex = 0
            if(currentQuestionIndex > firstQuestionIndex){

                //reset all button backgrounds
                val buttonBackgroundDefault = getDrawable(R.drawable.button_background)
                btnMultipleChoiceA.background = buttonBackgroundDefault
                btnMultipleChoiceB.background = buttonBackgroundDefault
                btnMultipleChoiceC.background = buttonBackgroundDefault
                btnMultipleChoiceD.background = buttonBackgroundDefault
                btnMultipleChoiceE.background = buttonBackgroundDefault

                //update which question we're on
                currentQuestionIndex--

                //change UI to reflect new question and possible answers
                updateQuestionAnswersText(questions[currentQuestionIndex])
            }
        }



        btnSubmit.setOnClickListener{
            //Display user score
            Toast.makeText(this, "You got a score of: $totalScore/${questions.size}", Toast.LENGTH_LONG).show()

            //delete all previous question answers and reshuffle question answers
            for(question in questions){
                question.userAnswer = "UNANSWERED"
                question.shuffleAnswers()
            }

            //reset all button backgrounds
            val buttonBackgroundDefault = getDrawable(R.drawable.button_background)
            btnMultipleChoiceA.background = buttonBackgroundDefault
            btnMultipleChoiceB.background = buttonBackgroundDefault
            btnMultipleChoiceC.background = buttonBackgroundDefault
            btnMultipleChoiceD.background = buttonBackgroundDefault
            btnMultipleChoiceE.background = buttonBackgroundDefault

            //reset score
            totalScore = 0

            //reset question index
            currentQuestionIndex = 0

            //reset question text and possible answers
            updateQuestionAnswersText(questions[currentQuestionIndex])
        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun updateQuestionAnswersText(question:Question){
        val potentialAnswers = question.potentialAnswers
        val questionText = question.questionText

        var potentialAnswersText = ""
        val uppercaseASCIIAlphabet = 65 //represents where the first letter of the alphabet starts in the ASCII table (A)

        //format possible answers ex: "[A] PossibleAnswer"
        for(i in 0 until potentialAnswers.size){
            potentialAnswersText +="[" + (i+uppercaseASCIIAlphabet).toChar() + "] " +potentialAnswers[i] + "\n"
        }

        txtAnswers.text = potentialAnswersText
        txtQuestion.text = questionText

        //change button background if the user already answered the question
        if(question.userAnswer != "UNANSWERED"){

            //map button letters to button widgets
            val buttonWidgetMap:HashMap<String, Button> = HashMap()
            buttonWidgetMap["A"] = btnMultipleChoiceA
            buttonWidgetMap["B"] = btnMultipleChoiceB
            buttonWidgetMap["C"] = btnMultipleChoiceC
            buttonWidgetMap["D"] = btnMultipleChoiceD
            buttonWidgetMap["E"] = btnMultipleChoiceE

            val lastChosenButton = buttonWidgetMap[question.userAnswer]
            if (lastChosenButton != null) {
                updateButtonBackground(lastChosenButton)
            }
        }
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

        //reset all button backgrounds
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

        val userAnswerIndex: Int = buttonAnswerMap[buttonLetter]!!
        val userAnswer = question.potentialAnswers[userAnswerIndex]

        //check if answer is correct or not
        if (userAnswer == question.answer){
            return true
        }
        return false
    }

}
