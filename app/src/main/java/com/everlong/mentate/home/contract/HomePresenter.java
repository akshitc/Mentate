package com.everlong.mentate.home.contract;

import com.everlong.mentate.util.Constants;
import com.everlong.mentate.util.KeyValueStore;

/**
 * Created by akshit on 3/19/17.
 */

public class HomePresenter implements HomeContract.Presenter {

    private HomeContract.View view;
    private KeyValueStore keyValueStore;

    public HomePresenter(HomeContract.View view, KeyValueStore keyValueStore) {
        this.view = view;
        this.keyValueStore = keyValueStore;
    }

    @Override
    public void setViews() {
        int bestScore = keyValueStore.getInt(Constants.KEY_BEST_SCORE, -1);
        if (bestScore != -1) {
            view.setBestScore(bestScore);
        } else {
            view.hideBestScore();
        }
    }

    @Override
    public void onStartClicked() {
        view.launchGame();
    }
}
