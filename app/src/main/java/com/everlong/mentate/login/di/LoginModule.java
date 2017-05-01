package com.everlong.mentate.login.di;

import com.everlong.mentate.login.contract.LoginContract;
import com.everlong.mentate.login.contract.LoginPresenter;
import com.everlong.mentate.util.KeyValueStore;

import dagger.Module;
import dagger.Provides;

/**
 * Created by akshit on 3/17/17.
 */
@Module
public class LoginModule {

    public LoginContract.View view;

    public LoginModule(LoginContract.View view) {
        this.view = view;
    }

    @Provides
    LoginPresenter provideLoginPresenter(KeyValueStore keyValueStore) {
        return new LoginPresenter(view, keyValueStore);
    }
}
