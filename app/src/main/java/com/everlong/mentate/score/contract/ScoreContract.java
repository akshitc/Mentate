package com.everlong.mentate.score.contract;

/**
 * Created by akshit on 4/11/17.
 */

public interface ScoreContract {

    interface View {

        void finishActivity();
    }

    interface Presenter {

        void saveBestScore(int score);

        void onBackToHomeClicked();
    }
}
