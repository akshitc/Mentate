package com.everlong.mentate.game.models;

import java.util.ArrayList;

/**
 * Created by akshit on 3/25/17.
 */

public class GameDataResponseModel {

    public Integer category_id;
    public String category_name;
    public String category_description;
    public ArrayList<Question> questions;

    public Integer getCategoryId() {
        return category_id;
    }

    public void setCategory_id(Integer category_id) {
        this.category_id = category_id;
    }

    public String getCategoryName() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getCategoryDescription() {
        return category_description;
    }

    public void setCategory_description(String category_description) {
        this.category_description = category_description;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }
}
