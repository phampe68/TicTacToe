package com.comp1601.truefalsequiz;

public class Question {
    private String question;
    private String answer;

    /**
     *
     * @param questionString
     */
    public Question(String questionString){
        //questionString is expected to be of the form:
        //"Is Java an Object-Oriented Language?[Yes]"
        String[] splitQuestion = questionString.split("\\[");
        splitQuestion[1] = splitQuestion[1].replace("]", "");

        question = splitQuestion[0];
        answer =splitQuestion[1];

    }

    public String getQuestion(){
        return answer;
    }

    public String getAnswer(){
        return question;
    }
}
