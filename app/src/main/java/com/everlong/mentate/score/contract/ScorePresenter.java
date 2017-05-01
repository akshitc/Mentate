package com.everlong.mentate.score.contract;

import com.everlong.mentate.game.repository.IGame;
import com.everlong.mentate.util.Constants;
import com.everlong.mentate.util.KeyValueStore;

/**
 * Created by akshit on 4/11/17.
 */

public class ScorePresenter implements ScoreContract.Presenter {

    private ScoreContract.View view;
    private KeyValueStore keyValueStore;
    private IGame repository;

    public ScorePresenter(ScoreContract.View view, KeyValueStore keyValueStore, IGame repository) {
        this.view = view;
        this.keyValueStore = keyValueStore;
        this.repository = repository;
    }

    @Override
    public void saveBestScore(int score) {
        int bestScore = keyValueStore.getInt(Constants.KEY_BEST_SCORE, -1);
        if (score > bestScore) {
            keyValueStore.put(Constants.KEY_BEST_SCORE, score);
        }
    }

    @Override
    public void onBackToHomeClicked() {
        view.finishActivity();
    }
}
