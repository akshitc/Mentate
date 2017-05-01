package com.everlong.mentate.game.models;

import java.util.List;

/**
 * Created by akshit on 3/25/17.
 */
public class Question {

    public String question;
    public List<String> options;
    public String image_url;
    public String correct_answer;
    public String passage_text;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public String getImageUrl() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getCorrectAnswer() {
        return correct_answer;
    }

    public void setCorrect_answer(String correct_answer) {
        this.correct_answer = correct_answer;
    }

    public String getPassageText() {
        return passage_text;
    }

    public void setPassage_text(String passage_text) {
        this.passage_text = passage_text;
    }
}
