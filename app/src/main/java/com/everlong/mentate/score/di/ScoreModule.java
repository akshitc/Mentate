package com.everlong.mentate.score.di;

import com.everlong.mentate.game.repository.IGame;
import com.everlong.mentate.score.contract.ScoreContract;
import com.everlong.mentate.score.contract.ScorePresenter;
import com.everlong.mentate.util.KeyValueStore;

import dagger.Module;
import dagger.Provides;

/**
 * Created by akshit on 4/11/17.
 */
@Module
public class ScoreModule {

    private ScoreContract.View view;

    public ScoreModule(ScoreContract.View view) {
        this.view = view;
    }

    @Provides
    ScorePresenter providesScorePresenter(KeyValueStore keyValueStore, IGame repository) {
        return new ScorePresenter(view, keyValueStore, repository);
    }
}
