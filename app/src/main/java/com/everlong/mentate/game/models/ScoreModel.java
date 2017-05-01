package com.everlong.mentate.game.models;

/**
 * Created by akshit on 4/11/17.
 */

public class ScoreModel {

    public String name;
    public String age;
    public String gender;
    public int score;

    public ScoreModel() {
        // Default constructor required for calls to DataSnapshot.getValue(ScoreModel.class)
    }

    public ScoreModel(String name, String age, String gender, int score) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.score = score;
    }
}
