package com.everlong.mentate.game.di;

import com.everlong.mentate.game.contract.GameContract;
import com.everlong.mentate.game.contract.GamePresenter;
import com.everlong.mentate.game.repository.IGame;
import com.everlong.mentate.util.KeyValueStore;

import dagger.Module;
import dagger.Provides;

/**
 * Created by akshit on 3/25/17.
 */
@Module
public class GameModule {
    private GameContract.View view;

    public GameModule(GameContract.View view) {
        this.view = view;
    }

    @Provides
    GamePresenter providesHomePresenter(KeyValueStore keyValueStore, IGame repository) {
        return new GamePresenter(view, repository, keyValueStore);
    }
}
