package com.everlong.mentate.home.contract;

/**
 * Created by akshit on 3/19/17.
 */

public interface HomeContract {

    interface View {

        void launchGame();

        void setBestScore(int bestScore);

        void hideBestScore();
    }

    interface Presenter {

        void onStartClicked();

        void setViews();
    }
}
