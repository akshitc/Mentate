package com.everlong.mentate.home.di;

import com.everlong.mentate.home.contract.HomeContract;
import com.everlong.mentate.home.contract.HomePresenter;
import com.everlong.mentate.util.KeyValueStore;

import dagger.Module;
import dagger.Provides;

/**
 * Created by akshit on 3/19/17.
 */
@Module
public class HomeModule {

    private HomeContract.View view;

    public HomeModule(HomeContract.View view) {
        this.view = view;
    }

    @Provides
    HomePresenter providesHomePresenter(KeyValueStore keyValueStore) {
        return new HomePresenter(view, keyValueStore);
    }
}
