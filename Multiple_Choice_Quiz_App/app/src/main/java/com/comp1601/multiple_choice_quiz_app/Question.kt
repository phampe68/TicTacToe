package com.comp1601.multiple_choice_quiz_app



/**
 * @param questionString: a string containing the question followed by its answer in square brackets
 * ex: "Is Java an Object-Oriented Language?[E]"
 */
class Question (questionString: String){

    //Define class properties
    private var question: String
    private var answer: String


    init {
        //class initializer code:
        val splitQuestion: MutableList<String> = questionString.split("\\[").toMutableList()
        splitQuestion[1] = splitQuestion[1].replace("]", "")
        this.question = splitQuestion[0]
        this.answer = splitQuestion[1]
    }


    public fun getQuestion():String {return question}
    public fun getAnswer():String {return answer}


}