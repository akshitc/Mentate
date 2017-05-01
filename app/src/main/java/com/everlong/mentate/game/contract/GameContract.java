package com.everlong.mentate.game.contract;

import com.everlong.mentate.game.models.Question;

/**
 * Created by akshit on 3/25/17.
 */

public interface GameContract {

    interface View {

        void showProgressBar();

        void hideProgressBar();

        void setCategoryName(String name);

        void setCategoryDescription(String description);

        void setCategoryDataVisibility(boolean isVisible);

        void initQuestionView(int categoryID);

        void setQuestionView(Question question, int questionIndex);

        void showToast();

        void removeGameView();

        void launchScoreActivity(int score);
    }

    interface Presenter {

        void fetchData();

        void onProceedClicked();

        void setCurrentQuestion();

        void onNextClicked(String selectedOption);
    }
}
