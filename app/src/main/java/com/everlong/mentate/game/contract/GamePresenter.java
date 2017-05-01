package com.everlong.mentate.game.contract;

import com.everlong.mentate.core.BasePresenter;
import com.everlong.mentate.game.models.GameDataResponseModel;
import com.everlong.mentate.game.models.Question;
import com.everlong.mentate.game.models.ScoreModel;
import com.everlong.mentate.game.repository.IGame;
import com.everlong.mentate.util.Constants;
import com.everlong.mentate.util.KeyValueStore;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.GenericTypeIndicator;

import java.util.ArrayList;
import java.util.List;

import rx.Observer;

/**
 * Created by akshit on 3/25/17.
 */

public class GamePresenter extends BasePresenter implements GameContract.Presenter, Observer<DataSnapshot> {

    private KeyValueStore keyValueStore;
    private GameContract.View view;
    private IGame repository;
    private List<GameDataResponseModel> gameDataList;
    private int totalCategoryCount;
    private int curCategoryIndex;
    private int curQuestionIndex;
    private int totalQuestionCount;
    private int score;
    private ArrayList<Question> questionList;

    public GamePresenter(GameContract.View view, IGame repository, KeyValueStore keyValueStore) {
        this.view = view;
        this.repository = repository;
        this.keyValueStore = keyValueStore;
    }

    @Override
    public void fetchData() {
        view.showProgressBar();
        unSubscribeAll();
        subscribe(repository.getGameData(), this);
    }

    @Override
    public void onCompleted() {
        view.hideProgressBar();
        initGame();
    }

    private void initGame() {
        totalCategoryCount = gameDataList.size();
        curCategoryIndex = 0;
        setCategory();
    }

    private void setCategory() {
        if (curCategoryIndex < totalCategoryCount) {
            GameDataResponseModel model = gameDataList.get(curCategoryIndex);
            String name = model.getCategoryName();
            String description = model.getCategoryDescription();
            view.removeGameView();
            view.setCategoryName(name);
            view.setCategoryDescription(description);
            view.setCategoryDataVisibility(true);
        } else {
            saveScore();
            view.launchScoreActivity(score);
        }
    }

    @Override
    public void onError(Throwable e) {
        view.hideProgressBar();
    }

    @Override
    public void onNext(DataSnapshot dataSnapshot) {
        GenericTypeIndicator<List<GameDataResponseModel>> t =
                new GenericTypeIndicator<List<GameDataResponseModel>>() {
                };
        gameDataList = dataSnapshot.getValue(t);
    }

    @Override
    public void onProceedClicked() {
        view.setCategoryDataVisibility(false);
        initQuestion();
    }

    private void initQuestion() {
        questionList = gameDataList.get(curCategoryIndex).getQuestions();
        totalQuestionCount = questionList.size();
        curQuestionIndex = 0;
        view.initQuestionView(gameDataList.get(curCategoryIndex).getCategoryId());
    }

    @Override
    public void setCurrentQuestion() {
        if (curQuestionIndex < totalQuestionCount) {
            view.setQuestionView(questionList.get(curQuestionIndex), curQuestionIndex);
        } else {
            curCategoryIndex++;
            setCategory();
        }
    }

    @Override
    public void onNextClicked(String selectedOption) {
        if (selectedOption != null) {
            updateScore(selectedOption);
            curQuestionIndex++;
            setCurrentQuestion();
        } else {
            view.showToast();
        }
    }

    private void updateScore(String selectedOption) {
        if (questionList.get(curQuestionIndex).getCorrectAnswer().equals(selectedOption)) {
            score += 1;
        }
    }

    private void saveScore() {
        String name = keyValueStore.get(Constants.KEY_NAME, "");
        String age = keyValueStore.get(Constants.KEY_AGE, "");
        String gender = keyValueStore.get(Constants.KEY_GENDER, "");

        ScoreModel scoreModel = new ScoreModel(name, age, gender, score);
        repository.saveScore(scoreModel);
    }
}
