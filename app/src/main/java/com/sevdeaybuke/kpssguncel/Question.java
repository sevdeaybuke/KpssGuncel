package com.sevdeaybuke.kpssguncel;

/**
 * Created by sevdeaybuke on 18.7.2017.
 */

public class Question {

    private String questionText;
    private String choice_a;
    private String choice_b;
    private String choice_c;
    private String choice_d;
    private String choice_e;
    private String correctAnswer;

    public Question() {
    }

    public Question(String questionText, String choice_a, String choice_b, String choice_c, String choice_d, String choice_e, String correctAnswer) {
        this.questionText = questionText;
        this.choice_a = choice_a;
        this.choice_b = choice_b;
        this.choice_c = choice_c;
        this.choice_d = choice_d;
        this.choice_e = choice_e;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getChoice_a() {
        return choice_a;
    }

    public void setChoice_a(String choice_a) {
        this.choice_a = choice_a;
    }

    public String getChoice_b() {
        return choice_b;
    }

    public void setChoice_b(String choice_b) {
        this.choice_b = choice_b;
    }

    public String getChoice_c() {
        return choice_c;
    }

    public void setChoice_c(String choice_c) {
        this.choice_c = choice_c;
    }

    public String getChoice_d() {
        return choice_d;
    }

    public void setChoice_d(String choice_d) {
        this.choice_d = choice_d;
    }

    public String getChoice_e() {
        return choice_e;
    }

    public void setChoice_e(String choice_e) {
        this.choice_e = choice_e;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public boolean isCorrectAnswer(String selectedAnswerText){

        return selectedAnswerText.matches(correctAnswer);

    }
}
